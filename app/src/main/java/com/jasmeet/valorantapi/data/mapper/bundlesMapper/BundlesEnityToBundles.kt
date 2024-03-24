package com.jasmeet.valorantapi.data.mapper.bundlesMapper

import com.jasmeet.valorantapi.data.model.local.BundlesEntity
import com.jasmeet.valorantapi.data.model.remote.bundleDetails.Data

fun BundlesEntity.toBundles():Data{
    return Data(
         assetPath= "",
     description= "",
     displayIcon= displayIcon,
     displayIcon2= displayIcon2,
     displayName= displayName,
     displayNameSubText= "",
     extraDescription= "",
     logoIcon= "",
     promoDescription= "",
     useAdditionalContext= false,
     uuid= uuid,
     verticalPromoImage= verticalPromoImage

    )
}