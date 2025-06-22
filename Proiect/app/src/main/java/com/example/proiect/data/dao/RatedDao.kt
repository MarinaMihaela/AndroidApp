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

    @Query("SELECT rating FROM rated WHERE userId = :userId AND filmId = :filmId LIMIT 1")
    fun getRating(userId: String, filmId: Int): Flow<Int?>

    @Query("""
    SELECT f.id, f.nume, f.descriere, f.durata, f.gen, f.actori, f.regizori, f.poster, r.rating
    FROM film f 
    INNER JOIN rated r ON f.id = r.filmId 
    WHERE r.userId = :userId
    ORDER BY f.nume
  """)
    fun getRatedForUser(userId: String): Flow<List<FilmRating>>
}
