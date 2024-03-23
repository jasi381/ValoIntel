package com.jasmeet.valorantapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasmeet.valorantapi.data.dao.CurrenciesDao
import com.jasmeet.valorantapi.data.model.local.CurrenciesEntity

/**
* Created by Jasmeet Singh
*/

@Database(entities = [CurrenciesEntity::class], version = 1, exportSchema = false)
abstract class CurrenciesDatabase:RoomDatabase() {
    abstract fun currenciesDao(): CurrenciesDao

}