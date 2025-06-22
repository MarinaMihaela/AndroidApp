package com.example.proiect.data.model

import androidx.room.Entity

@Entity(
    tableName = "rated",
    primaryKeys = ["userId", "filmId"]
)
data class Rated(
    val userId: String,
    val filmId: Int,
    val rating: Int
)
