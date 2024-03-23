package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.SprayEntity

@Dao
interface SpraysDao {
    @Query("SELECT * FROM sprays")
    suspend fun getAllSprays(): List<SprayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSprays(sprays: List<SprayEntity>)
}