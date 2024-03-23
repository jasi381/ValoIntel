package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.MapsDao
import com.jasmeet.valorantapi.data.model.local.MapsEntity

@Database(entities = [MapsEntity::class], version = 1, exportSchema = false)
abstract class MapsDatabase :RoomDatabase(){
    abstract fun mapsDao(): MapsDao
}
