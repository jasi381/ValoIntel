package com.jasmeet.valorantapi.di

import android.content.Context
import androidx.room.Room
import com.jasmeet.valorantapi.repository.AgentsRepository
import com.jasmeet.valorantapi.room.AgentsDao
import com.jasmeet.valorantapi.room.AgentsDatabase
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
    fun provideMyDatabase(@ApplicationContext context: Context): AgentsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AgentsDatabase::class.java,
            "agent_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesAgentsDao(database: AgentsDatabase):AgentsDao{
        return database.agentsDao()
    }


    @Provides
    @Singleton
    fun providesAgentsRepository(agentsDao: AgentsDao):AgentsRepository{
        return AgentsRepository(agentsDao)
    }
}