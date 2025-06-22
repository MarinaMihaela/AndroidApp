package com.example.proiect.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proiect.data.model.Vizionate
import com.example.proiect.data.model.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface VizionateDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun markWatched(entry: Vizionate): Long

    @Delete
    fun unmarkWatched(entry: Vizionate): Int

    @Query("""
    SELECT f.* 
    FROM film f 
    INNER JOIN vizionate v ON f.id = v.filmId 
    WHERE v.userId = :userId
    ORDER BY f.nume
  """)
    fun getWatchedForUser(userId: String): Flow<List<Film>>
}
