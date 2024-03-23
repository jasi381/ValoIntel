package com.jasmeet.valorantapi.data.model.remote.playerCardDetails

data class Data(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val largeArt: String,
    val smallArt: String,
    val themeUuid: Any,
    val uuid: String,
    val wideArt: String
)