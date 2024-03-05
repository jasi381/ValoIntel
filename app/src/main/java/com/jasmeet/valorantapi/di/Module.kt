package com.jasmeet.valorantapi.di

import android.content.Context
import androidx.room.Room
import com.jasmeet.valorantapi.repository.AgentsRepository
import com.jasmeet.valorantapi.repository.WeaponsRepository
import com.jasmeet.valorantapi.room.agents.AgentsDao
import com.jasmeet.valorantapi.room.agents.AgentsDatabase
import com.jasmeet.valorantapi.room.weapons.WeaponsDao
import com.jasmeet.valorantapi.room.weapons.WeaponsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Module {


    @Provides
    @Singleton
    fun providesAgentsDatabase(@ApplicationContext context: Context): AgentsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AgentsDatabase::class.java,
            "agent_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesAgentsDao(database: AgentsDatabase): AgentsDao {
        return database.agentsDao()
    }


    @Provides
    @Singleton
    fun providesAgentsRepository(agentsDao: AgentsDao):AgentsRepository{
        return AgentsRepository(agentsDao)
    }

    @Provides
    @Singleton
    fun provideWeaponsDatabase(@ApplicationContext context: Context): WeaponsDatabase {
      return  Room.databaseBuilder(
          context.applicationContext,
          WeaponsDatabase::class.java,
          "weapons_database"
      ).build()
    }

    @Provides
    @Singleton
    fun providesWeaponsDao(database: WeaponsDatabase): WeaponsDao {
        return database.weaponsDao()
    }


    @Provides
    @Singleton
    fun providesWeaponsRepository(weaponsDao: WeaponsDao):WeaponsRepository{
        return WeaponsRepository(weaponsDao)
    }
}