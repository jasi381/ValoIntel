package com.jasmeet.valorantapi.data.mapper.spraysMapper

import com.jasmeet.valorantapi.data.model.local.SprayEntity
import com.jasmeet.valorantapi.data.model.remote.spraysApiResponse.Data

fun SprayEntity.toSpray(): Data {
    return Data(
         animationGif =animationGif.toString(),
     animationPng = "",
     assetPath = "",
     category = "",
     displayIcon = displayIcon.toString(),
     displayName = displayName,
     fullIcon = fullIcon.toString(),
     fullTransparentIcon = fullTransparentIcon.toString(),
     hideIfNotOwned = false,
     isNullSpray = false,
     levels = emptyList(),
     themeUuid = "",
     uuid = uuid

    )
}