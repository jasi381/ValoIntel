package com.jasmeet.valorantapi.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.jasmeet.valorantapi.service.WeaponsUpdateService

@Suppress("DEPRECATION")
class WeaponsNetworkChangeReceiver: BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                val intent1 = Intent(context, WeaponsUpdateService::class.java)
                context.startService(intent1
                )
            }
        }
    }
}