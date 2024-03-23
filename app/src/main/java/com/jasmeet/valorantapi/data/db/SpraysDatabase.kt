package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.SpraysDao
import com.jasmeet.valorantapi.data.model.local.SprayEntity

/**
 * Created by Jasmeet Singh
 */

@Database(entities = [SprayEntity::class], version = 1, exportSchema = false)
abstract class SpraysDatabase: RoomDatabase() {
    abstract fun spraysDao(): SpraysDao

}