package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.BundleDao
import com.jasmeet.valorantapi.data.model.local.BundlesEntity

@Database(entities = [BundlesEntity::class], version = 1, exportSchema = false)
abstract class BundlesDatabase : RoomDatabase() {
    abstract fun bundlesDao(): BundleDao
}