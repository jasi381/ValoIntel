package com.jasmeet.valorantapi.data.model.remote.buddyDetails

data class Level(
    val assetPath: String,
    val charmLevel: Int,
    val displayIcon: String,
    val displayName: String,
    val hideIfNotOwned: Boolean,
    val uuid: String
)