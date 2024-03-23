package com.jasmeet.valorantapi.data.di

import android.content.Context
import androidx.room.Room
import com.jasmeet.valorantapi.data.dao.AgentsDao
import com.jasmeet.valorantapi.data.dao.BuddiesDao
import com.jasmeet.valorantapi.data.dao.BundleDao
import com.jasmeet.valorantapi.data.dao.CurrenciesDao
import com.jasmeet.valorantapi.data.dao.MapsDao
import com.jasmeet.valorantapi.data.dao.PlayerCardsDao
import com.jasmeet.valorantapi.data.dao.SpraysDao
import com.jasmeet.valorantapi.data.dao.WeaponsDao
import com.jasmeet.valorantapi.data.db.AgentsDatabase
import com.jasmeet.valorantapi.data.db.BuddiesDatabase
import com.jasmeet.valorantapi.data.db.BundlesDatabase
import com.jasmeet.valorantapi.data.db.CurrenciesDatabase
import com.jasmeet.valorantapi.data.db.MapsDatabase
import com.jasmeet.valorantapi.data.db.PlayerCardDatabase
import com.jasmeet.valorantapi.data.db.SpraysDatabase
import com.jasmeet.valorantapi.data.db.WeaponsDatabase
import com.jasmeet.valorantapi.data.repository.AgentsRepository
import com.jasmeet.valorantapi.data.repository.BuddiesRepository
import com.jasmeet.valorantapi.data.repository.BundlesRepository
import com.jasmeet.valorantapi.data.repository.CurrenciesRepository
import com.jasmeet.valorantapi.data.repository.MapsRepository
import com.jasmeet.valorantapi.data.repository.PlayerCardsRepository
import com.jasmeet.valorantapi.data.repository.SpraysRepository
import com.jasmeet.valorantapi.data.repository.WeaponsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Module {

    /**
     * Provides Agents Database, Dao and Repository
     */
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

    /**
     * Provides Weapons Database, Dao and Repository
     */

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

    /**
     * Provides Maps Database, Dao and Repository
     */

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

    /**
     * Provides Currency Database, Dao and Repository
     */
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


    /**
     * Provides Sprays Database, Dao and Repository
     */
    @Provides
    @Singleton
    fun providesSpraysDatabase(@ApplicationContext context: Context): SpraysDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            SpraysDatabase::class.java,
            "sprays_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesSpraysDao(database: SpraysDatabase): SpraysDao {
        return database.spraysDao()
    }


    @Provides
    @Singleton
    fun providesSpraysRepository(spraysDao: SpraysDao): SpraysRepository {
        return SpraysRepository(spraysDao)
    }

    /**
     * Provides Buddies Database, Dao and Repository
     */

    @Provides
    @Singleton
    fun providesBuddiesDatabase(@ApplicationContext context: Context): BuddiesDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            BuddiesDatabase::class.java,
            "buddies_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBuddiesDao(database: BuddiesDatabase): BuddiesDao {
        return database.buddiesDao()
    }


    @Provides
    @Singleton
    fun providesBuddiesRepository(buddiesDao: BuddiesDao): BuddiesRepository {
        return BuddiesRepository(buddiesDao)
    }

    /**
     * Provides PlayerCards Database, Dao and Repository
     */

    @Provides
    @Singleton
    fun providesPlayerCardsDatabase(@ApplicationContext context: Context): PlayerCardDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            PlayerCardDatabase::class.java,
            "playerCards_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesPlayerCardsDao(database: PlayerCardDatabase): PlayerCardsDao {
        return database.playerCardDao()
    }


    @Provides
    @Singleton
    fun providesPlayerCardsRepository(playerCardsDao: PlayerCardsDao): PlayerCardsRepository {
        return PlayerCardsRepository(playerCardsDao)
    }


    /**
     * Provides Bundles Database, Dao and Repository
     */

    @Provides
    @Singleton
    fun providesBundlesDatabase(@ApplicationContext context: Context): BundlesDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            BundlesDatabase::class.java,
            "bundles_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBundlesDao(database: BundlesDatabase): BundleDao {
        return database.bundlesDao()
    }


    @Provides
    @Singleton
    fun providesBundlesRepository(bundleDao: BundleDao): BundlesRepository {
        return BundlesRepository(bundleDao)
    }





}