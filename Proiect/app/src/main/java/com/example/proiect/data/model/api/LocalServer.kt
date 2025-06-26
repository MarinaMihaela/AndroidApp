package com.example.proiect.data.model.api

import android.util.Log
import fi.iki.elonen.NanoHTTPD
import com.example.proiect.data.db.AppDatabase
import com.example.proiect.data.model.Film
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer

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

                if (session.method == Method.GET) {
                    // GET: returneaza lista de filme
                    val filme: List<Film> = runBlocking {
                        filmDao.getAll().first()
                    }
                    val json = Json.encodeToString(filme)
                    newFixedLengthResponse(Response.Status.OK, "application/json", json)

                } else if (session.method == Method.POST) {
                    try {
                        // Preluam body-ul POST
                        val map = HashMap<String, String>()
                        session.parseBody(map)
                        val requestBody = map["postData"] ?: ""

                        // Decodam lista de filme din JSON
                        val filmePrimite = Json.decodeFromString<List<Film>>(requestBody)

                        // Inseram filmele in baza de date (sincron)
                        runBlocking {
                            filmDao.insertAll(filmePrimite)
                        }

                        newFixedLengthResponse(Response.Status.OK, "text/plain", "Filmele au fost adaugate cu succes")

                    } catch (e: Exception) {
                        Log.e("MyApp", "Eroare la POST /filme", e)
                        newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "Eroare: ${e.localizedMessage}")
                    }
                } else {
                    newFixedLengthResponse(Response.Status.METHOD_NOT_ALLOWED, "text/plain", "Metoda nu este permisa")
                }
            }

            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not found")
        }
    }

}
