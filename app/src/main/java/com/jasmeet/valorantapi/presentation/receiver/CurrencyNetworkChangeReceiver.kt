package com.jasmeet.valorantapi.presentation.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.jasmeet.valorantapi.presentation.service.MapsUpdateService


@Suppress("DEPRECATION")
class CurrencyNetworkChangeReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                val intent1 = Intent(context, CurrencyNetworkChangeReceiver::class.java)
                context.startService(intent1
                )
            }
        }
    }
}
