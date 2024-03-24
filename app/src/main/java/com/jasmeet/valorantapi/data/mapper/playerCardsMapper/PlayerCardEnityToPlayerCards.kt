package com.jasmeet.valorantapi.data.mapper.playerCardsMapper

import com.jasmeet.valorantapi.data.model.local.PlayerCardEntity
import com.jasmeet.valorantapi.data.model.remote.playerCardDetails.Data

fun PlayerCardEntity.toPlayerCards():Data{
    return Data(
         assetPath = "",
     displayIcon = displayIcon.toString(),
     displayName = displayName,
     isHiddenIfNotOwned = false,
     largeArt = "",
     smallArt = "",
     themeUuid = "",
     uuid = uuid,
     wideArt =""

    )
}