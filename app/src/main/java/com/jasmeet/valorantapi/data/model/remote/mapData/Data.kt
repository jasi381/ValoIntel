package com.jasmeet.valorantapi.data.model.remote.mapData

data class Data(
    val assetPath: String,
    val callouts: List<Callout>,
    val coordinates: String,
    val displayIcon: String,
    val displayName: String,
    val listViewIcon: String,
    val listViewIconTall: String,
    val mapUrl: String,
    val narrativeDescription: String,
    val premierBackgroundImage: String,
    val splash: String,
    val stylizedBackgroundImage: String,
    val tacticalDescription: String,
    val uuid: String,
    val xMultiplier: Double,
    val xScalarToAdd: Double,
    val yMultiplier: Double,
    val yScalarToAdd: Double
)