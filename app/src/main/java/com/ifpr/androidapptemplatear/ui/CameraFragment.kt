package com.ifpr.androidapptemplatear.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.ar.core.Config
import com.google.ar.core.TrackingState
import com.ifpr.androidapptemplatear.R
import com.ifpr.androidapptemplatear.databinding.FragmentCameraBinding
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class CameraFragment : Fragment(R.layout.fragment_camera) {
    private var binding: FragmentCameraBinding? = null
    private var modelNode: ArModelNode? = null
    private lateinit var state: SharedModelViewModel
    private var currentModel = "sensor.glb"
    private var loaded = false
    private var player: MediaPlayer? = null
    private var generation = 0
    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) loadModel(currentModel)
        else binding?.status?.setText(R.string.camera_required)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        state = ViewModelProvider(requireActivity())[SharedModelViewModel::class.java]
        binding?.sceneView?.lightEstimationMode = Config.LightEstimationMode.DISABLED
        binding?.place?.setOnClickListener { placeModel() }
        binding?.collect?.setOnClickListener { collectModel() }
        binding?.resetGame?.setOnClickListener {
            state.resetCollection()
            loadModel(currentModel)
        }
        binding?.sound?.setOnClickListener {
            state.soundEnabled = !state.soundEnabled
            updateSound()
        }
        state.collection.observe(viewLifecycleOwner) { progress ->
            binding?.score?.text = getString(R.string.score_format, progress.size * 10, progress.size)
        }
        state.selectedModel.observe(viewLifecycleOwner) { path ->
            currentModel = path
            if (hasCameraPermission()) loadModel(path)
        }
        if (hasCameraPermission()) {
            if (modelNode == null) loadModel(currentModel)
        } else permission.launch(Manifest.permission.CAMERA)
        updateSound()
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        requireContext(), Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    private fun clearModel() {
        generation++
        loaded = false
        modelNode?.let { node ->
            binding?.sceneView?.removeChild(node)
            node.destroy()
        }
        modelNode = null
        binding?.place?.isEnabled = false
        binding?.collect?.isEnabled = false
    }

    private fun loadModel(path: String) {
        val ui = binding ?: return
        if (!hasCameraPermission()) return
        clearModel()
        if (path in state.collection.value.orEmpty()) {
            ui.status.setText(R.string.already_collected)
            return
        }
        ui.status.setText(R.string.loading_model)
        val token = generation
        val node = ArModelNode(ui.sceneView.engine, PlacementMode.PLANE_HORIZONTAL)
        modelNode = node
        ui.sceneView.addChild(node)
        node.isEditable = true
        node.isScaleEditable = false
        node.loadModelGlbAsync(
            glbFileLocation = path,
            scaleToUnits = 0.4f,
            centerOrigin = Position(0f, -1f, 0f),
            onError = {
                if (token == generation) {
                    binding?.status?.setText(R.string.model_error)
                    binding?.place?.isEnabled = false
                }
            }
        ) {
            if (token == generation && binding != null) {
                loaded = true
                binding?.place?.isEnabled = true
                binding?.sceneView?.planeRenderer?.isVisible = true
                binding?.status?.text = getString(R.string.position_model, path.removeSuffix(".glb"))
            }
        }
    }

    private fun placeModel() {
        val node = modelNode ?: return
        if (!loaded) return
        if (node.anchor() == null) {
            Toast.makeText(requireContext(), R.string.find_surface, Toast.LENGTH_SHORT).show()
            return
        }
        // Keep a stable anchor while the user collects the object.
        node.isEditable = false
        binding?.sceneView?.planeRenderer?.isVisible = false
        binding?.place?.isEnabled = false
        binding?.collect?.isEnabled = currentModel in CollectionProgress.targets
        binding?.status?.setText(if (currentModel in CollectionProgress.targets) R.string.ready_collect else R.string.model_placed)
    }

    private fun collectModel() {
        val node = modelNode ?: return
        if (!loaded || node.anchor?.trackingState != TrackingState.TRACKING) return
        if (state.collect(currentModel)) {
            clearModel()
            binding?.status?.setText(if (state.collection.value.orEmpty().size == 2) R.string.mission_complete else R.string.next_object)
        }
    }

    private fun updateSound() {
        binding?.sound?.setText(if (state.soundEnabled) R.string.sound_on else R.string.sound_off)
        if (state.soundEnabled && isResumed) {
            if (player == null) player = MediaPlayer.create(requireContext(), R.raw.ambiente)?.apply {
                isLooping = true
                setVolume(0.25f, 0.25f)
            }
            player?.start()
        } else if (player?.isPlaying == true) player?.pause()
    }

    override fun onResume() {
        super.onResume()
        if (::state.isInitialized) updateSound()
    }

    override fun onPause() {
        player?.pause()
        super.onPause()
    }

    override fun onDestroyView() {
        clearModel()
        player?.release()
        player = null
        binding = null
        super.onDestroyView()
    }
}
