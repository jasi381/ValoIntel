package com.jasmeet.valorantapi.data.di

import android.content.Context
import androidx.room.Room
import com.jasmeet.valorantapi.data.repository.AgentsRepository
import com.jasmeet.valorantapi.data.repository.CurrenciesRepository
import com.jasmeet.valorantapi.data.repository.MapsRepository
import com.jasmeet.valorantapi.data.repository.WeaponsRepository
import com.jasmeet.valorantapi.data.dao.AgentsDao
import com.jasmeet.valorantapi.data.db.AgentsDatabase
import com.jasmeet.valorantapi.data.dao.CurrenciesDao
import com.jasmeet.valorantapi.data.db.CurrenciesDatabase
import com.jasmeet.valorantapi.data.dao.MapsDao
import com.jasmeet.valorantapi.data.db.MapsDatabase
import com.jasmeet.valorantapi.data.dao.WeaponsDao
import com.jasmeet.valorantapi.data.db.WeaponsDatabase
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
    fun providesAgentsRepository(agentsDao: AgentsDao): AgentsRepository {
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
    fun providesWeaponsRepository(weaponsDao: WeaponsDao): WeaponsRepository {
        return WeaponsRepository(weaponsDao)
    }


    @Provides
    @Singleton
    fun providesMapsDatabase(@ApplicationContext context: Context): MapsDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            MapsDatabase::class.java,
            "maps_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesMapsDao(database: MapsDatabase): MapsDao {
        return database.mapsDao()
    }


    @Provides
    @Singleton
    fun providesMapsRepository(mapsDao: MapsDao): MapsRepository {
        return MapsRepository(mapsDao)
    }

    @Provides
    @Singleton
    fun providesCurrencyDatabase(@ApplicationContext context: Context): CurrenciesDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            CurrenciesDatabase::class.java,
            "currency_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesCurrencyDao(database: CurrenciesDatabase): CurrenciesDao {
        return database.currenciesDao()
    }


    @Provides
    @Singleton
    fun providesCurrencyRepository(currenciesDao: CurrenciesDao): CurrenciesRepository {
        return CurrenciesRepository(currenciesDao)
    }
}