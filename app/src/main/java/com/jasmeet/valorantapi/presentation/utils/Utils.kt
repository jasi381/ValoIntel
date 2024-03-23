package com.jasmeet.valorantapi.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import kotlin.math.log10


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

     fun makeApiCall(
         url :String,
         id:String?=null
     ): String {

         val newUrl = if (id != null) {"$url/$id"} else{ url }

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(
                httpLoggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()


        val request = Request.Builder()
            .url(newUrl)
            .build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.string() ?: ""
            } else {
                throw IOException("Error: ${response.code}")
            }
        } catch (e: IOException) {
            throw e
        }

    }
    fun getCategoryName(input: String): String {
        val categoryName = input.substringAfter("::")
        return categoryName
    }

    fun formatScientificNotation(value: Double): String {
        val coefficient = value.toString().substringBefore('E').toDouble()
        val exponent = value.toString().substringAfter('E').toInt()
        return "${coefficient.toInt()} X 10^$exponent"
    }



}
