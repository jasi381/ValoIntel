package com.jasmeet.valorantapi.data.mapper.bundlesMapper

import com.jasmeet.valorantapi.data.model.local.BundlesEntity
import com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse.Data


fun Data.toBundlesEntity(): BundlesEntity {
    return BundlesEntity(
        uuid = uuid ,
        displayName = displayName,
        displayIcon = displayIcon,
        displayIcon2 = displayIcon2,
        verticalPromoImage = verticalPromoImage
    )
}