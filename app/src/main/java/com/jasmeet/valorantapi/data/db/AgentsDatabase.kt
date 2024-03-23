package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasmeet.valorantapi.data.converters.AgentConverter
import com.jasmeet.valorantapi.data.dao.AgentsDao
import com.jasmeet.valorantapi.data.model.local.AgentsEntity

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
@TypeConverters(AgentConverter::class)
abstract class AgentsDatabase : RoomDatabase() {
    abstract fun agentsDao(): AgentsDao
}