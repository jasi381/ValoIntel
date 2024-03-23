package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.BuddiesEntity

@Dao
interface BuddiesDao {
    @Query("SELECT * FROM buddies")
    suspend fun getAllBuddies(): List<BuddiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuddy(buddies: List<BuddiesEntity>)
}