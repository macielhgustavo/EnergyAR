package com.ifpr.androidapptemplatear.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedModelViewModel : ViewModel() {
    val selectedModel = MutableLiveData("sensor.glb")
    private var progress = CollectionProgress()
    private val _collection = MutableLiveData(progress.collected)
    val collection: LiveData<Set<String>> = _collection
    var soundEnabled = true

    fun selectModel(modelPath: String) {
        selectedModel.value = modelPath
    }

    fun collect(path: String): Boolean {
        val next = progress.collect(path)
        if (next == progress) return false
        progress = next
        _collection.value = progress.collected
        return true
    }

    fun resetCollection() {
        progress = CollectionProgress()
        _collection.value = progress.collected
    }
}
