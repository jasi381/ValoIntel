package com.jasmeet.valorantapi.room.agents

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDatabase : RoomDatabase() {
    abstract fun agentsDao(): AgentsDao
}