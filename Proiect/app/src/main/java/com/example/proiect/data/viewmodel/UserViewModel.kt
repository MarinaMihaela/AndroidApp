package com.example.proiect.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proiect.data.db.DatabaseProvider
import com.example.proiect.data.repo.UserRepository
import com.example.proiect.data.model.User
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    // Suggestion: inject UserRepository (via DI) instead of instantiating here for easier testing
    private val repo = UserRepository(
        DatabaseProvider.getDatabase(app).userDao()
    )

    fun upsertUser(user: User) = viewModelScope.launch {
        repo.upsert(user)
    }

    fun getById(userId: String): LiveData<User?> =
        repo.getById(userId).asLiveData()

    fun getByEmail(email: String): LiveData<User?> =
        repo.getByEmail(email).asLiveData()
}
