package com.jasmeet.valorantapi.presentation.service

import android.app.IntentService
import android.content.Intent
import com.jasmeet.valorantapi.ValoIntelApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class MapsUpdateService : IntentService("MapsUpdateService") {
    override fun onHandleIntent(intent: Intent?) {
        val appContext = this.applicationContext
        val repository = (appContext as ValoIntelApp).mapsRepository

        GlobalScope.launch {
            repository.fetchMaps(appContext)
        }
    }
}
