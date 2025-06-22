package com.example.proiect.data.repo

import com.example.proiect.data.dao.FilmDao
import com.example.proiect.data.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.flow

class FilmRepository(private val dao: FilmDao) {

    suspend fun insertAll(films: List<Film>): List<Long> = withContext(Dispatchers.IO) {
        dao.insertAll(films)
    }

    fun getAll(): Flow<List<Film>> =
        dao.getAll()

    fun getById(filmId: Int): Flow<Film?> = flow {
        emit(dao.getById(filmId))
    }
}
