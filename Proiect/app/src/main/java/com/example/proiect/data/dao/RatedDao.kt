package com.example.proiect.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proiect.data.model.Rated
import com.example.proiect.data.model.FilmRating
import kotlinx.coroutines.flow.Flow

@Dao
interface RatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun rateFilm(entry: Rated): Long

    @Query("SELECT rating FROM Rated WHERE userId = :userId AND filmId = :filmId")
    fun getRating(userId: String, filmId: Int): Flow<Int?>

    @Query("SELECT * FROM Rated WHERE userId = :userId")
    fun getRatedForUser(userId: String): Flow<List<Rated>>
}

