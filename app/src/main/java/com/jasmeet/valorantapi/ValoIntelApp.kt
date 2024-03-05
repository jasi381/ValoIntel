package com.jasmeet.valorantapi

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.jasmeet.valorantapi.repository.AgentsRepository
import com.jasmeet.valorantapi.repository.WeaponsRepository
import com.jasmeet.valorantapi.room.agents.AgentsDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class ValoIntelApp:Application(),ImageLoaderFactory{

    @Inject
    lateinit var agentsRepository: AgentsRepository

    @Inject
    lateinit var weaponsRepository: WeaponsRepository


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