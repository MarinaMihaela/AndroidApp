package com.example.proiect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.proiect.data.model.api.HttpHelper
import com.example.proiect.data.model.api.LocalServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.activity.enableEdgeToEdge

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proiect.data.model.Film
import com.example.proiect.data.model.api.FilmDataSource
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    private lateinit var server: LocalServer

    override fun onCreate(savedInstanceState: Bundle?) {

                super.onCreate(savedInstanceState)

        // Grab references
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNav    = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // Wire up bottom nav to navigation controller
        bottomNav.setupWithNavController(navController)

        // Hide on login/register; show everywhere else
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.visibility = if (
                destination.id == R.id.loginFragment ||
                destination.id == R.id.registerFragment
            ) View.GONE else View.VISIBLE
        }


      
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
//        CoroutineScope(Dispatchers.IO).launch {
//            val helloResponse = HttpHelper.httpGet("http://localhost:8080/hello")
//            android.util.Log.d("MyApp", "Raspuns /hello: $helloResponse")
//        }

        // Test: request catre /filme
//        CoroutineScope(Dispatchers.IO).launch {
//            val filmeResponse = HttpHelper.httpGet("http://localhost:8080/filme")
//            android.util.Log.d("MyApp", "Raspuns /filme: $filmeResponse")
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val jsonBody = Json.encodeToString(ListSerializer(Film.serializer()), FilmDataSource.filmeInitiale)
//                val postResponse = HttpHelper.httpPostFilme("http://10.0.2.2:8080/filme", jsonBody)
//
//
//                Log.d("MainActivity", "POST /filme response: $postResponse")
//            } catch (e: Exception) {
//                Log.e("MainActivity", "POST error: ${e.message}", e)
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stopServer()
    }
}
