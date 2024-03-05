package com.jasmeet.valorantapi.service

import android.app.IntentService
import android.content.Intent
import com.jasmeet.valorantapi.ValoIntelApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AgentsUpdateService : IntentService("AgentsUpdateService") {
    override fun onHandleIntent(intent: Intent?) {
        val appContext = this.applicationContext
        val repository = (appContext as ValoIntelApp).agentsRepository

        GlobalScope.launch {
            repository.fetchAgents(appContext)
        }
    }
}
