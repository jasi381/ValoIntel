package com.jasmeet.valorantapi.mapper

import com.jasmeet.valorantapi.data.Data
import com.jasmeet.valorantapi.room.data.AgentsEntity

fun Data.toAgentsEntity(): AgentsEntity {
    return uuid?.let {
        AgentsEntity(
            uuid = it,
            abilities = abilities,
            backgroundGradientColors = backgroundGradientColors,
            background = background.toString(),
            description = description.toString(),
            displayName = displayName.toString(),
            fullPortrait = fullPortrait.toString(),
            fullPortraitV2 = fullPortraitV2.toString(),
            bustPortrait = bustPortrait.toString(),
            developerName = developerName.toString()
        )
    }!!
}