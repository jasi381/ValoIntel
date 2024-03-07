package com.jasmeet.valorantapi

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.jasmeet.valorantapi.navigation.ValoIntelNavigation
import com.jasmeet.valorantapi.receiver.AgentsNetworkChangeReceiver
import com.jasmeet.valorantapi.receiver.WeaponsNetworkChangeReceiver
import com.jasmeet.valorantapi.ui.theme.ValorantApiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val agentsNetworkChangeReceiver = AgentsNetworkChangeReceiver()
    private val weaponsNetworkChangeReceiver = WeaponsNetworkChangeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val agentsFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(agentsNetworkChangeReceiver, agentsFilter)


        val weaponsFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(weaponsNetworkChangeReceiver, weaponsFilter)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            ValorantApiTheme {
                val navHostController = rememberNavController()
                ValoIntelNavigation(navHostController = navHostController)

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(agentsNetworkChangeReceiver)
        unregisterReceiver(weaponsNetworkChangeReceiver)
    }
}









