package com.example.proiect.data.model.api

import android.content.Context
import android.util.Log
import com.example.proiect.data.db.AppDatabase
import com.example.proiect.data.model.Film
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class LocalHttpServer(private val context: Context) : NanoHTTPD(8080) {

    override fun serve(session: IHTTPSession?): Response {
        val uri = session?.uri

        return runBlocking {

            val uri = session?.uri ?: "null"
            Log.d("LocalHttpServer", "Request URI: $uri")
            val responseJson = when (uri) {
                "/hello" -> {
                    """{"message":"Hello from local server!"}"""
                }
//                "/filme" -> {
//                    val db = AppDatabase.getDatabase(context)
//                    val filmDao = db.filmDao()
//                    val all = filmDao.getAll().first()  // extrage lista din Flow
//                    toJson(all)
//                }
                else -> """{"error":"Route not found"}"""
            }

            newFixedLengthResponse(Response.Status.OK, "application/json", responseJson)
        }
    }

    private fun toJson(data: List<Film>): String {
        return Json.encodeToString(
            ListSerializer(Film.serializer()),
            data
        )
    }
}
