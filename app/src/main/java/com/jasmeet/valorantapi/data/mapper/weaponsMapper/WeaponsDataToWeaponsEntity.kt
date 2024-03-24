package com.jasmeet.valorantapi.data.mapper.weaponsMapper


import com.jasmeet.valorantapi.data.model.local.WeaponsEntity
import com.jasmeet.valorantapi.data.model.remote.weaponsApiResponse.Data

fun Data.toWeaponsEntity(): WeaponsEntity {
    return WeaponsEntity(
        uuid = uuid,
        displayName= displayName,
        displayIcon = displayIcon,
        category= category,
        skins = skins
    )
}
