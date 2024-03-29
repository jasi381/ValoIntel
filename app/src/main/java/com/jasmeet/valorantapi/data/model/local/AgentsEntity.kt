package com.jasmeet.valorantapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasmeet.valorantapi.data.model.remote.agentsApiResponse.Ability


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



