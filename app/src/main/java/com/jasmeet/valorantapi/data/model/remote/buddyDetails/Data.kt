package com.jasmeet.valorantapi.data.model.remote.buddyDetails

data class Data(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val levels: List<Level>,
    val themeUuid: Any,
    val uuid: String
)