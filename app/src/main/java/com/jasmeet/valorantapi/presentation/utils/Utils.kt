package com.jasmeet.valorantapi.presentation.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.URL


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

    fun getDominantColorFromUrl(imageUrl: String): Color {
        // Download the image from the URL
        val inputStream = URL(imageUrl).openStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Generate the palette and get the dominant color
        val palette = Palette.from(bitmap).generate()
        return Color(palette.dominantSwatch?.rgb ?: Color.White.toArgb())
    }

    fun getDominantColorsFromUrl(imageUrl: String): Pair<Color, Color> {
        // Download the image from the URL
        val inputStream = URL(imageUrl).openStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Generate the palette and get the dominant and second dominant colors
        val palette = Palette.from(bitmap).generate()
        val dominantColor = Color(palette.dominantSwatch?.rgb ?: Color.White.toArgb())
        val secondDominantColor = Color(palette.swatches.sortedByDescending { it.population }[1]?.rgb ?: Color.White.toArgb())

        return Pair(dominantColor, secondDominantColor)
    }





}



