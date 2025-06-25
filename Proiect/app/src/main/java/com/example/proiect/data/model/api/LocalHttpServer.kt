package com.example.proiect.data.model.api

import android.content.Context
import android.util.Log
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.runBlocking

class LocalHttpServer(private val context: Context) : NanoHTTPD(9099) {

    override fun serve(session: IHTTPSession?): Response {
        Log.d("LocalHttpServer", "Received something")  // LOG că a intrat în serve()

        val uri = session?.uri ?: "null"
        Log.d("LocalHttpServer", "Request URI: $uri")

        return runBlocking {
            val responseJson = when (uri) {
                "/hello" -> """{"message":"Hello from local server!"}"""
                else -> """{"error":"Route not found"}"""
            }
            newFixedLengthResponse(Response.Status.OK, "application/json", responseJson)
        }
    }
}
