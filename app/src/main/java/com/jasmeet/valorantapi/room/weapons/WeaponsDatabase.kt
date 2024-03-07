package com.jasmeet.valorantapi.room.weapons

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasmeet.valorantapi.converters.WeaponsConverter


@Database(entities = [WeaponsEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeaponsConverter::class)
abstract class WeaponsDatabase:RoomDatabase() {
    abstract  fun weaponsDao(): WeaponsDao
}