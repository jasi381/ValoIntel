package com.jasmeet.valorantapi.room.agents

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.valorantapi.data.agentsApiResponse.Ability


@Entity(tableName = "agents")
data class AgentsEntity(
    @PrimaryKey
    val uuid: String,
    val abilities: List<Ability>,
    val backgroundGradientColors: List<String>,
    val background: String,
    val description: String,
    val displayName: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val bustPortrait: String,
    val developerName: String,
)



