package com.jasmeet.valorantapi.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.valorantapi.data.weaponsApiResponse.Skin

class WeaponsConverter {

    @TypeConverter
    fun fromSkinsList(skins :List<Skin>):String{
        val gson = Gson()
        return gson.toJson(skins)
    }

    @TypeConverter
    fun toSkinsList(skinsStrings :String):List<Skin>{
        val gson = Gson()
        val type = object:TypeToken<List<Skin>>() {}.type
        return gson.fromJson(skinsStrings,type)

    }
}