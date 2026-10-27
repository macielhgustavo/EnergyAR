package com.ifpr.androidapptemplatear.ui

/** A model scores once per round, and only the two mission objects score. */
data class CollectionProgress(val collected: Set<String> = emptySet()) {
    fun collect(path: String): CollectionProgress =
        if (path in targets) copy(collected = collected + path) else this

    companion object {
        val targets = setOf("sensor.glb", "filtro.glb")
    }
}
