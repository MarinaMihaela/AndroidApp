package com.example.proiect.data.model.api

import com.example.proiect.data.model.Film
import retrofit2.http.GET

interface MovieApi {
    @GET("movies")
    suspend fun getMovies(): List<Film>
}