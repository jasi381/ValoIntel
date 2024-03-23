package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.WeaponsEntity


@Dao
interface WeaponsDao {

    @Query("SELECT * FROM weapons")
    suspend fun getAllWeapons (): List<WeaponsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapons (weapons: List<WeaponsEntity>)

}