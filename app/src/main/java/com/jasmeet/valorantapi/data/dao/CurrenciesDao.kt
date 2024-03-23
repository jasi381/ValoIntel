package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.CurrenciesEntity


@Dao
interface CurrenciesDao {

    @Query("SELECT * FROM currency")
    suspend fun getAllCurrency(): List<CurrenciesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencies: List<CurrenciesEntity>)
}