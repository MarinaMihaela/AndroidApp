package com.example.proiect.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proiect.data.db.DatabaseProvider
import com.example.proiect.data.repo.VizionateRepository
import com.example.proiect.data.model.Vizionate
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class VizionateViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = VizionateRepository(
        DatabaseProvider.getDatabase(app).vizionateDao()
    )

    fun markWatched(userId: String, filmId: Int) = viewModelScope.launch {
        // Consider wrapping in try/catch to handle potential DB errors gracefully
        repo.markWatched(Vizionate(userId, filmId))
    }

    fun unmarkWatched(userId: String, filmId: Int) = viewModelScope.launch {
        repo.unmarkWatched(Vizionate(userId, filmId))
    }

    private val db = DatabaseProvider.getDatabase(app)
    private val vizRepo = VizionateRepository(db.vizionateDao())
    private val filmDao = db.filmDao()

    fun getWatchedForUser(userId: String) =
        vizRepo.getWatchedForUser(userId)
            .flatMapLatest { vizList ->
                flow {
                    // for each Vizionate entry, look up its film by ID
                    val films = vizList.mapNotNull { v ->
                        filmDao.getById(v.id)
                    }
                    emit(films)
                }
            }
            .asLiveData()

}
