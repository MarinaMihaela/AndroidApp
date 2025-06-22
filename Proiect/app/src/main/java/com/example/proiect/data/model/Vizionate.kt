package com.example.proiect.data.model

import androidx.room.Entity

@Entity(
    tableName = "vizionate",
    primaryKeys = ["userId", "filmId"]
)
data class Vizionate(
    val userId: String,
    val filmId: Int
)
