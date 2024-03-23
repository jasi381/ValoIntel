package com.jasmeet.valorantapi.data.model.remote.sprayDetails

data class Data(
    val animationGif: Any,
    val animationPng: Any,
    val assetPath: String,
    val category: Any,
    val displayIcon: String,
    val displayName: String,
    val fullIcon: String,
    val fullTransparentIcon: String,
    val hideIfNotOwned: Boolean,
    val isNullSpray: Boolean,
    val levels: List<Level>,
    val themeUuid: Any,
    val uuid: String
)