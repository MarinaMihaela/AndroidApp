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

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getById(userId: String): Flow<User?>

    @Query("SELECT * FROM User WHERE email = :email")
    fun getByEmail(email: String): Flow<User?>

}
