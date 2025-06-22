package com.example.proiect.data.repo

import com.example.proiect.data.dao.UserDao
import com.example.proiect.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(private val dao: UserDao) {

    suspend fun upsert(user: User): Long = withContext(Dispatchers.IO) {
        dao.upsert(user)
    }

    fun getById(userId: String): Flow<User?> =
        dao.getById(userId)

    fun getByEmail(email: String): Flow<User?> =
        dao.getByEmail(email)
}
