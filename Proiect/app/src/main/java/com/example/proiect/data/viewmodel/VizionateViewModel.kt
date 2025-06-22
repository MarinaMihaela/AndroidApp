package com.example.proiect.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proiect.data.db.DatabaseProvider
import com.example.proiect.data.repo.VizionateRepository
import com.example.proiect.data.model.Vizionate
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

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

    fun getWatchedForUser(userId: String) =
        repo.getWatchedForUser(userId).asLiveData()
}
