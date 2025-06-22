package com.example.proiect.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(
    @PrimaryKey val id: Int,
    val nume: String,
    val descriere: String,
    val durata: Int,
    val gen: String,
    val actori: String,
    val regizori: String,
    val poster: String,
    val rating: Float?
)
