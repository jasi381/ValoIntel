package com.jasmeet.valorantapi.mapper

import com.jasmeet.valorantapi.data.weaponsApiResponse.Data
import com.jasmeet.valorantapi.room.weapons.WeaponsEntity

fun Data.toWeaponsEntity():WeaponsEntity{
    return WeaponsEntity(
        uuid = uuid,
        displayName= displayName,
        displayIcon = displayIcon,
        category= category,
        skins = skins
    )
}
