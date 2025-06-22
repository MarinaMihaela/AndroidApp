package com.example.proiect.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,    // uid din Firebase Auth
    val email: String,
    val name: String
)
