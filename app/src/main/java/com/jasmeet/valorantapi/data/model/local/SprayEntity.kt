package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sprays")
data class SprayEntity(
    @PrimaryKey
    val uuid : String,
    val displayName : String,
    val displayIcon: String?,
    val fullIcon : String?,
    val fullTransparentIcon : String?,
    val animationGif : String?,

)
