package com.jasmeet.valorantapi.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.valorantapi.data.agentsApiResponse.Ability

class AgentConverter {

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