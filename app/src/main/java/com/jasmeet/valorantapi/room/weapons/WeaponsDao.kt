package com.jasmeet.valorantapi.room.weapons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeaponsDao {

    @Query("SELECT * FROM weapons")
    suspend fun getAllWeapons (): List<WeaponsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapons (weapons: List<WeaponsEntity>)

}