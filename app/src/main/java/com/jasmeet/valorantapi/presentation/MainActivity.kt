package com.jasmeet.valorantapi.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.jasmeet.valorantapi.presentation.navigation.ValoIntelNavigation
import com.jasmeet.valorantapi.presentation.receiver.AgentsNetworkChangeReceiver
import com.jasmeet.valorantapi.presentation.receiver.CurrencyNetworkChangeReceiver
import com.jasmeet.valorantapi.presentation.receiver.MapsNetworkChangeReceiver
import com.jasmeet.valorantapi.presentation.receiver.WeaponsNetworkChangeReceiver
import com.jasmeet.valorantapi.presentation.theme.ValorantApiTheme
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val agentsNetworkChangeReceiver = AgentsNetworkChangeReceiver()
    private val weaponsNetworkChangeReceiver = WeaponsNetworkChangeReceiver()
    private val mapsNetworkChangeReceiver = MapsNetworkChangeReceiver()
    private val currencyNetworkChangeReceiver = CurrencyNetworkChangeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val agentsFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(agentsNetworkChangeReceiver, agentsFilter)

        val weaponsFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(weaponsNetworkChangeReceiver, weaponsFilter)

        val mapsFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(mapsNetworkChangeReceiver, mapsFilter)

        val currencyFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(currencyNetworkChangeReceiver, currencyFilter)


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
        unregisterReceiver(mapsNetworkChangeReceiver)
        unregisterReceiver(currencyNetworkChangeReceiver)
    }
}









