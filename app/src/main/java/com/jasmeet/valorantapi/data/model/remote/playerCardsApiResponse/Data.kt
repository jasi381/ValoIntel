package com.jasmeet.valorantapi.data.model.remote.playerCardsApiResponse

data class Data(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val largeArt: String,
    val smallArt: String,
    val themeUuid: String,
    val uuid: String,
    val wideArt: String
)