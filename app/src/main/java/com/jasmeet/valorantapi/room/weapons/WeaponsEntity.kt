package com.jasmeet.valorantapi.room.weapons

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weapons")
data class WeaponsEntity(

    @PrimaryKey
    val uuid:String,
    val displayName : String,
    val displayIcon:String,
    val category :String

)
