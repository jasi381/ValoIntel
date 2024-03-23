package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasmeet.valorantapi.data.converters.WeaponsConverter
import com.jasmeet.valorantapi.data.dao.WeaponsDao
import com.jasmeet.valorantapi.data.model.local.WeaponsEntity


@Database(entities = [WeaponsEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeaponsConverter::class)
abstract class WeaponsDatabase:RoomDatabase() {
    abstract  fun weaponsDao(): WeaponsDao
}