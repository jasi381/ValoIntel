package com.jasmeet.valorantapi.room.agents

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AgentsDao {

    @Query("SELECT * FROM agents")
    suspend fun getAllAgents(): List<AgentsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgents(agents: List<AgentsEntity>)

}