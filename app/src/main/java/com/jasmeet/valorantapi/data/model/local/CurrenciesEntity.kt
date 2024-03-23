package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "currency")
data class CurrenciesEntity(
    val displayName: String,
    val largeIcon: String,

    @PrimaryKey
    val uuid: String

)
