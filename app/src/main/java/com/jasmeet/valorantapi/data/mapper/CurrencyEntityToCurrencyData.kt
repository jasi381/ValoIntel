package com.jasmeet.valorantapi.data.mapper

import com.jasmeet.valorantapi.data.model.local.CurrenciesEntity
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data

fun CurrenciesEntity.toCurrenciesData(): Data {
    return Data(
        uuid = uuid,
        displayName = displayName,
        largeIcon = largeIcon,
        assetPath = "",
        displayIcon = "",
        displayNameSingular = ""
    )
}