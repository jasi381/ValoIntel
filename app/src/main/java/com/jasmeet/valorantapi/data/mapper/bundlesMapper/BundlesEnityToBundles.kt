package com.jasmeet.valorantapi.data.mapper.bundlesMapper

import com.jasmeet.valorantapi.data.model.local.BundlesEntity
import com.jasmeet.valorantapi.data.model.remote.bundlesApiResponse.Data


fun BundlesEntity.toBundles(): Data {
    return Data(
         assetPath= "",
     description= "",
     displayIcon= displayIcon.toString(),
     displayIcon2= displayIcon2.toString(),
     displayName= displayName,
     displayNameSubText= "",
     extraDescription= "",
     logoIcon= "",
     promoDescription= "",
     useAdditionalContext= false,
     uuid= uuid,
     verticalPromoImage= verticalPromoImage.toString()

    )
}