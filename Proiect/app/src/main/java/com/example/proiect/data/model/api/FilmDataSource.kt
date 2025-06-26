package com.example.proiect.data.model.api

import com.example.proiect.data.model.Film

object FilmDataSource {
    val filmeInitiale = listOf(
        Film(
            id = 1,
            nume = "Inception",
            descriere = "A mind-bending thriller",
            durata = 148,
            gen = "Sci-Fi",
            actori = "Leonardo DiCaprio",
            regizori = "Christopher Nolan",
            poster = "",
            rating = 4.5f
        ),
        Film(
            id = 2,
            nume = "Interstellar",
            descriere = "Exploring space and time",
            durata = 169,
            gen = "Sci-Fi",
            actori = "Matthew McConaughey",
            regizori = "Christopher Nolan",
            poster = "",
            rating = 4.8f
        ),
        Film(
            id = 3,
            nume = "The Dark Knight",
            descriere = "Batman faces the Joker in Gotham City",
            durata = 152,
            gen = "Action",
            actori = "Christian Bale, Heath Ledger",
            regizori = "Christopher Nolan",
            poster = "",
            rating = 4.9f
        ),
        Film(
            id = 4,
            nume = "The Matrix",
            descriere = "A hacker discovers reality is a simulation",
            durata = 136,
            gen = "Sci-Fi",
            actori = "Keanu Reeves",
            regizori = "Lana Wachowski, Lilly Wachowski",
            poster = "",
            rating = 4.7f
        ),
        Film(
            id = 5,
            nume = "Pulp Fiction",
            descriere = "Interwoven stories of crime in LA",
            durata = 154,
            gen = "Crime",
            actori = "John Travolta, Uma Thurman",
            regizori = "Quentin Tarantino",
            poster = "",
            rating = 4.6f
        )
    )
}
