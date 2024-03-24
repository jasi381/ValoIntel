package com.jasmeet.valorantapi.data.mapper.playerCardsMapper

import com.jasmeet.valorantapi.data.model.local.PlayerCardEntity
import com.jasmeet.valorantapi.data.model.remote.playerCardsApiResponse.Data

fun Data.toPlayerCardsEntity():PlayerCardEntity{
    return PlayerCardEntity(
        uuid = uuid,
        displayName = displayName,
        displayIcon = displayIcon
    )
}