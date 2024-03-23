package com.jasmeet.valorantapi.data.mapper

import com.jasmeet.valorantapi.data.model.local.CurrenciesEntity
import com.jasmeet.valorantapi.data.model.remote.currencyApiResponse.Data

fun Data.toCurrencyEntity(): CurrenciesEntity {
    return CurrenciesEntity(
        uuid = uuid,
        displayName = displayName,
        largeIcon = largeIcon
    )
}