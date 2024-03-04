package com.jasmeet.valorantapi.utils

import android.content.Context
import android.net.ConnectivityManager


object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    fun formatColorString(colorString: String): String {
        return if (colorString.length >= 6) {
            "#${colorString.substring(0, 6)}"
        } else {
            colorString
        }
    }




}
