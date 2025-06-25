package com.example.proiect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proiect.data.model.api.LocalHttpServer
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var localHttpServer: LocalHttpServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        localHttpServer = LocalHttpServer(this)
        try {
            localHttpServer.start()
            Log.d("LocalHttpServer", "Server started on port 8080")
        } catch (e: Exception) {
            Log.e("LocalHttpServer", "Error starting server", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        localHttpServer.stop()
        Log.d("LocalHttpServer", "Server stopped")
    }
}
