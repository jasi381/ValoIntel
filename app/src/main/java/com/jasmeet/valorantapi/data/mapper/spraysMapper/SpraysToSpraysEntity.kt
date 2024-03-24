package com.jasmeet.valorantapi.data.mapper.spraysMapper

import com.jasmeet.valorantapi.data.model.local.SprayEntity
import com.jasmeet.valorantapi.data.model.remote.spraysApiResponse.Data

fun Data.toSpraysEntity():SprayEntity{
    return SprayEntity(
        uuid  = uuid,
     displayName = displayName,
     displayIcon = displayIcon,
     fullIcon = fullIcon,
     fullTransparentIcon = fullTransparentIcon,
     animationGif = animationGif,
    )
}