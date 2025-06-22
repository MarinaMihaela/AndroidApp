package com.example.proiect.data.repo

import com.example.proiect.data.dao.VizionateDao
import com.example.proiect.data.model.Vizionate
import com.example.proiect.data.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class VizionateRepository(private val dao: VizionateDao) {

    suspend fun markWatched(entry: Vizionate): Long = withContext(Dispatchers.IO) {
        dao.markWatched(entry)
    }

    suspend fun unmarkWatched(entry: Vizionate): Int = withContext(Dispatchers.IO) {
        dao.unmarkWatched(entry)
    }

    fun getWatchedForUser(userId: String): Flow<List<Vizionate>> = dao.getWatchedForUser(userId)
}
