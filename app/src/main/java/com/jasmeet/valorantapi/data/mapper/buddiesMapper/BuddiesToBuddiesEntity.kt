package com.jasmeet.valorantapi.data.mapper.buddiesMapper

import com.jasmeet.valorantapi.data.model.local.BuddiesEntity
import com.jasmeet.valorantapi.data.model.remote.buddiesApiResponse.Data


fun Data.toBuddiesEntity():BuddiesEntity{
    return BuddiesEntity(
        uuid = uuid,
        displayName = displayName,
        displayIcon = displayIcon
    )
}