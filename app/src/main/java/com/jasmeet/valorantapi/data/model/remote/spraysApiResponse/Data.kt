package com.jasmeet.valorantapi.data.model.remote.spraysApiResponse

data class Data(
    val animationGif: String,
    val animationPng: String,
    val assetPath: String,
    val category: String,
    val displayIcon: String,
    val displayName: String,
    val fullIcon: String,
    val fullTransparentIcon: String,
    val hideIfNotOwned: Boolean,
    val isNullSpray: Boolean,
    val levels: List<Level>,
    val themeUuid: String,
    val uuid: String
)