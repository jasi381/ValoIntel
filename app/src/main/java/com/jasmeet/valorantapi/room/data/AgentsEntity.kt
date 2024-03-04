package com.jasmeet.valorantapi.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.valorantapi.data.apiResponse.Ability


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


class Converters {
    @TypeConverter
    fun fromAbilityList(abilities: List<Ability>): String {
        val gson = Gson()
        return gson.toJson(abilities)
    }

    @TypeConverter
    fun toAbilityList(abilitiesString: String): List<Ability> {
        val gson = Gson()
        val type = object : TypeToken<List<Ability>>() {}.type
        return gson.fromJson(abilitiesString, type)
    }

    @TypeConverter
    fun fromStringList(strings: List<String>): String {
        val gson = Gson()
        return gson.toJson(strings)
    }

    @TypeConverter
    fun toStringList(stringsString: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(stringsString, type)
    }
}

