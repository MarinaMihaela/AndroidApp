package com.example.proiect.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proiect.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User): Long

    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    fun getById(userId: String): Flow<User?>

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getByEmail(email: String): Flow<User?>
}
