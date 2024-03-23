package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maps")
data class MapsEntity(

    @PrimaryKey
    val uuid:String,
    val displayName: String,
    val listViewIcon: String,
)
