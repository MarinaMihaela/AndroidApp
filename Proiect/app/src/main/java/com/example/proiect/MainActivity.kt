package com.example.proiect
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.util.Log

import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MyApp"
    }

    lateinit var server: SimpleWebServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        findViewById(R.id.nav_host_fragment)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Pornim serverul local pe portul 8080
        server = SimpleWebServer(8080)
        server.start()
        Log.d(TAG, "Server pornit pe portul 8080")

        // Facem request GET în corutină
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = httpGet("http://localhost:8080/hello")
                Log.d(TAG, "Răspuns: $response")
            } catch (e: Exception) {
                Log.e(TAG, "Eroare la request", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stop()
        Log.d(TAG, "Server oprit")
    }

    private fun httpGet(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        return try {
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            val code = connection.responseCode
            if (code == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                "Error code: $code"
            }
        } finally {
            connection.disconnect()
        }
    }

    class SimpleWebServer(port: Int) : NanoHTTPD(port) {
        override fun serve(session: IHTTPSession?): Response {
            val uri = session?.uri ?: ""
            return if (uri == "/hello") {
                newFixedLengthResponse("Hello World")
            } else {
                newFixedLengthResponse("Not found")
            }
        }
    }
}