package com.example.proiect.data.model.api
import android.util.Log
import com.example.proiect.data.model.Film
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.OutputStreamWriter
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


    fun httpPostFilme(url: String, jsonBody: String): String {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")

            connection.outputStream.use { os ->
                os.write(jsonBody.toByteArray())
                os.flush()
            }

            val responseCode = connection.responseCode
            val inputStream = if (responseCode in 200..299) {
                connection.inputStream
            } else {
                connection.errorStream
            }

            val response = inputStream.bufferedReader().readText()
            Log.d("MyApp", "POST /filme response: $response")
            response
        } catch (e: Exception) {
            Log.e("MyApp", "Error in POST", e)
            "Error: ${e.localizedMessage}"
        }
    }
}