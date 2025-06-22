package com.example.proiect.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proiect.data.db.DatabaseProvider
import com.example.proiect.data.repo.FilmRepository
import com.example.proiect.data.model.Film
import kotlinx.coroutines.launch

class FilmsViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = FilmRepository(
        DatabaseProvider.getDatabase(app).filmDao()
    )

    // Improvement: consider using distinctUntilChanged() on LiveData to avoid unnecessary UI updates
    val films: LiveData<List<Film>> =
        repo.getAll().asLiveData()

    fun refreshFilms(filmsList: List<Film>) = viewModelScope.launch {
        repo.insertAll(filmsList)
    }
}
