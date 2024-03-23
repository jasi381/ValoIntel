package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.PlayerCardsDao
import com.jasmeet.valorantapi.data.model.local.PlayerCardEntity

/**
 * Created by Jasmeet Singh
 */

@Database(entities = [PlayerCardEntity::class], version = 1, exportSchema = false)
abstract class PlayerCardDatabase: RoomDatabase() {
    abstract fun playerCardDao(): PlayerCardsDao

}