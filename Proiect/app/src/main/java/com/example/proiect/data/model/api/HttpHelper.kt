package com.example.proiect.data.model.api
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

object HttpHelper {
    fun httpGet(url: String): String {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val response = connection.inputStream.bufferedReader().readText()
            Log.d("MyApp", "Răspuns: $response")
            response
        } catch (e: Exception) {
            Log.e("MyApp", "Eroare la request", e)
            "Eroare: ${e.localizedMessage}"
        }
    }
}