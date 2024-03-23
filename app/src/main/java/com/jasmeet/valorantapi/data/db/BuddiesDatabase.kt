package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.BuddiesDao
import com.jasmeet.valorantapi.data.model.local.BuddiesEntity

/**
 * Created by Jasmeet Singh
 */

@Database(entities = [BuddiesEntity::class], version = 1, exportSchema = false)
abstract class BuddiesDatabase: RoomDatabase() {
    abstract fun buddiesDao(): BuddiesDao

}