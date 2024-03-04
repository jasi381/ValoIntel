package com.jasmeet.valorantapi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasmeet.valorantapi.room.data.AgentsEntity
import com.jasmeet.valorantapi.room.data.Converters

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AgentsDatabase : RoomDatabase() {
    abstract fun agentsDao(): AgentsDao

    companion object {
        @Volatile
        private var INSTANCE: AgentsDatabase? = null

        fun getInstance(context: Context): AgentsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgentsDatabase::class.java,
                    "agents_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}