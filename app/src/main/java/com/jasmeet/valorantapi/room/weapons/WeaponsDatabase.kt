package com.jasmeet.valorantapi.room.weapons

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.room.agents.AgentsEntity


@Database(entities = [WeaponsEntity::class], version = 1, exportSchema = false)
abstract class WeaponsDatabase:RoomDatabase() {
    abstract  fun weaponsDao(): WeaponsDao
}