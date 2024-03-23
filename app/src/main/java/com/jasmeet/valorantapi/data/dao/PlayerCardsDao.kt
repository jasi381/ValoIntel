package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.PlayerCardEntity

@Dao
interface PlayerCardsDao {
    @Query("SELECT * FROM player_card")
    suspend fun getAllPlayerCards(): List<PlayerCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerCard(playerCard: List<PlayerCardEntity>)
}