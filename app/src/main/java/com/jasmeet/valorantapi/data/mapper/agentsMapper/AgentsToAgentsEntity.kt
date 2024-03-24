package com.jasmeet.valorantapi.data.mapper.agentsMapper

import com.jasmeet.valorantapi.data.model.local.AgentsEntity
import com.jasmeet.valorantapi.data.model.remote.agentsApiResponse.Data

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