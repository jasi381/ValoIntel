package com.jasmeet.valorantapi

import android.app.Application
import com.jasmeet.valorantapi.repository.AgentsRepository
import com.jasmeet.valorantapi.room.AgentsDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ValoIntelApp:Application() {

    val agentsRepository :AgentsRepository
        get() = AgentsRepository(
            AgentsDatabase.getInstance(this).agentsDao()
        )
}