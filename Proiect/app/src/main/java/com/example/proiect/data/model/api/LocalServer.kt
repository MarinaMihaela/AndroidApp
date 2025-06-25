package com.example.proiect.data.model.api

import android.util.Log
import fi.iki.elonen.NanoHTTPD
import com.example.proiect.data.db.AppDatabase
import com.example.proiect.data.model.Film
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
class LocalServer(private val context: android.content.Context, port: Int = 8080) : NanoHTTPD(port) {

    fun startServer() {
        try {
            start()
            Log.d("MyApp", "Server pornit pe portul $listeningPort")
        } catch (e: Exception) {
            Log.e("MyApp", "Eroare la pornirea serverului", e)
        }
    }

    fun stopServer() {
        stop()
        Log.d("MyApp", "Server oprit")
    }

    override fun serve(session: IHTTPSession): Response {
        return when (session.uri) {
            "/hello" -> newFixedLengthResponse(Response.Status.OK, "text/plain", "Hello World")

            "/filme" -> {
                val db = AppDatabase.getDatabase(context)
                val filmDao = db.filmDao()

                val filme: List<Film> = runBlocking {
                    filmDao.getAll().first()
                }

                val json = Json.encodeToString(filme)
                newFixedLengthResponse(Response.Status.OK, "application/json", json)
            }

            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not found")
        }
    }
}