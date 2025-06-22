package com.example.proiect.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proiect.data.model.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(films: List<Film>): List<Long>

    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<Film>>

    @Query("SELECT * FROM film WHERE id = :filmId")
    fun getById(filmId: Int): Film?

}
