package com.jasmeet.valorantapi.data.mapper.mapsMapper

import com.jasmeet.valorantapi.data.model.local.MapsEntity
import com.jasmeet.valorantapi.data.model.remote.mapsData.Data

fun Data.toMapsEntity(): MapsEntity {
    return MapsEntity(
       uuid = uuid,
        displayName = displayName,
        listViewIcon = listViewIcon
    )
}