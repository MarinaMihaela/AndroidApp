package com.example.proiect.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proiect.data.db.DatabaseProvider
import com.example.proiect.data.repo.RatedRepository
import com.example.proiect.data.model.Rated
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData


class RatedViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = RatedRepository(
        DatabaseProvider.getDatabase(app).ratedDao()
    )

    fun rateFilm(userId: String, filmId: Int, rating: Int) = viewModelScope.launch {
        repo.rateFilm(Rated(userId, filmId, rating))
    }

    fun getRating(userId: String, filmId: Int): LiveData<Int?> =
        repo.getRating(userId, filmId).asLiveData()

    // Suggestion: use Transformations.switchMap if userId can change in the same ViewModel
    fun getRatedForUser(userId: String) =
        repo.getRatedForUser(userId).asLiveData()
}
