package com.example.proiect.data.repo

import com.example.proiect.data.dao.RatedDao
import com.example.proiect.data.model.Rated
import com.example.proiect.data.model.FilmRating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RatedRepository(private val dao: RatedDao) {

    suspend fun rateFilm(entry: Rated): Long = withContext(Dispatchers.IO) {
        dao.rateFilm(entry)
    }

    fun getRating(userId: String, filmId: Int): Flow<Int?> =
        dao.getRating(userId, filmId)

    fun getRatedForUser(userId: String): Flow<List<FilmRating>> =
        dao.getRatedForUser(userId)
}
