package com.jasmeet.valorantapi

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.jasmeet.valorantapi.data.repository.AgentsRepository
import com.jasmeet.valorantapi.data.repository.CurrenciesRepository
import com.jasmeet.valorantapi.data.repository.MapsRepository
import com.jasmeet.valorantapi.data.repository.WeaponsRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class ValoIntelApp:Application(),ImageLoaderFactory{

    @Inject
    lateinit var agentsRepository: AgentsRepository

    @Inject
    lateinit var weaponsRepository: WeaponsRepository

    @Inject
    lateinit var mapsRepository: MapsRepository

    @Inject
    lateinit var currencyRepository: CurrenciesRepository


    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this)
            .newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache{

                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()

    }

}