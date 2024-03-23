package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.MapsEntity

@Dao
interface MapsDao {

    @Query("SELECT * FROM maps")
    suspend fun getAllMaps(): List<MapsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaps(maps: List<MapsEntity>)
}