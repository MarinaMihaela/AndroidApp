package com.example.proiect.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proiect.data.dao.*
import com.example.proiect.data.model.*

@Database(
    entities = [ User::class,
        Film::class,
        Vizionate::class,
        Rated::class ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun filmDao(): FilmDao
    abstract fun vizionateDao(): VizionateDao
    abstract fun ratedDao(): RatedDao
}
