package com.jasmeet.valorantapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jasmeet.valorantapi.room.data.AgentsEntity

@Dao
interface AgentsDao {

    @Query("SELECT * FROM agents")
    suspend fun getAllAgents(): List<AgentsEntity>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAgents(agents: List<AgentsEntity>)

}