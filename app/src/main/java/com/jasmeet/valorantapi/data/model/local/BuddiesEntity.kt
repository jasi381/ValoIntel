package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "buddies")
data class BuddiesEntity(
    @PrimaryKey
    val uuid : String,
    val displayName : String,
    val displayIcon: String?,
)