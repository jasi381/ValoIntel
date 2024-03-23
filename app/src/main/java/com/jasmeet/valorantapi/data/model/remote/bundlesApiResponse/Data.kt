package com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse

data class Data(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayIcon2: String,
    val displayName: String,
    val displayNameSubText: String,
    val extraDescription: String,
    val logoIcon: String,
    val promoDescription: String,
    val useAdditionalContext: Boolean,
    val uuid: String,
    val verticalPromoImage: String
)