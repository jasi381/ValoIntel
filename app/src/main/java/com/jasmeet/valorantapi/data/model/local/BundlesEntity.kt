package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bundles")
data class BundlesEntity(
    @PrimaryKey()
    val uuid: String,
    val displayName: String,
    val displayIcon: String?,
    val displayIcon2: String?,
    val verticalPromoImage: String?,

)
