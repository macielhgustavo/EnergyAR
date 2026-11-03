package com.ifpr.androidapptemplatear

import com.ifpr.androidapptemplatear.ui.CollectionProgress
import org.junit.Assert.*
import org.junit.Test

class CollectionProgressTest {
    @Test fun duplicateCollectionDoesNotScoreTwice() {
        val progress = CollectionProgress().collect("sensor.glb").collect("sensor.glb")
        assertEquals(setOf("sensor.glb"), progress.collected)
    }

    @Test fun onlyMissionObjectsAreAccepted() {
        val progress = CollectionProgress().collect("sofa.glb").collect("sensor.glb").collect("filtro.glb")
        assertEquals(CollectionProgress.targets, progress.collected)
        assertTrue(CollectionProgress().collected.isEmpty())
    }
}
