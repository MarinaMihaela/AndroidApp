package com.example.proiect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proiect.data.model.api.HttpHelper
import com.example.proiect.data.model.api.LocalServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.activity.enableEdgeToEdge

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {

    private lateinit var server: LocalServer

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

        // Porneste serverul local
        server = LocalServer(applicationContext)
        server.startServer()

        // Test: request catre /hello
        CoroutineScope(Dispatchers.IO).launch {
            val helloResponse = HttpHelper.httpGet("http://localhost:8080/hello")
            android.util.Log.d("MyApp", "Raspuns /hello: $helloResponse")
        }

        // Test: request catre /filme
        CoroutineScope(Dispatchers.IO).launch {
            val filmeResponse = HttpHelper.httpGet("http://localhost:8080/filme")
            android.util.Log.d("MyApp", "Raspuns /filme: $filmeResponse")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stopServer()
    }
}
