package com.jasmeet.valorantapi.data.mapper.buddiesMapper

import com.jasmeet.valorantapi.data.model.local.BuddiesEntity
import com.jasmeet.valorantapi.data.model.remote.buddyDetails.Data

fun BuddiesEntity.toBuddiesData():Data{
    return Data(
        uuid = uuid,
        assetPath = "",
        displayName = displayName,
        displayIcon = displayIcon,
        isHiddenIfNotOwned = false,
        levels = emptyList(),
        themeUuid =  ""

    )
}