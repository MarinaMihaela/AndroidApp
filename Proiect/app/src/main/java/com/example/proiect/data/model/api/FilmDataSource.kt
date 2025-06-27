package com.example.proiect.data.model.api

import com.example.proiect.data.model.Film
import com.example.proiect.R

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
            poster = "poster_1",
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
            poster = "poster_2",
            rating = 4.8f
        ),
        Film(
            id = 3,
            nume = "The Dark Knight",
            descriere = "Batman battles the Joker in Gotham City",
            durata = 152,
            gen = "Action",
            actori = "Christian Bale, Heath Ledger",
            regizori = "Christopher Nolan",
            poster = "poster_3",
            rating = 4.9f
        ),
        Film(
            id = 4,
            nume = "The Shawshank Redemption",
            descriere = "Two imprisoned men bond over years",
            durata = 142,
            gen = "Drama",
            actori = "Tim Robbins, Morgan Freeman",
            regizori = "Frank Darabont",
            poster = "poster_4",
            rating = 5.0f
        ),
        Film(
            id = 5,
            nume = "Pulp Fiction",
            descriere = "Crime stories intertwined in LA",
            durata = 154,
            gen = "Crime",
            actori = "John Travolta, Uma Thurman",
            regizori = "Quentin Tarantino",
            poster = "poster_5",
            rating = 4.7f
        ),
        Film(
            id = 6,
            nume = "Forrest Gump",
            descriere = "Life story of a kind-hearted man",
            durata = 142,
            gen = "Drama",
            actori = "Tom Hanks",
            regizori = "Robert Zemeckis",
            poster = "poster_6",
            rating = 4.8f
        ),
        Film(
            id = 7,
            nume = "The Matrix",
            descriere = "A hacker discovers reality is a simulation",
            durata = 136,
            gen = "Sci-Fi",
            actori = "Keanu Reeves",
            regizori = "The Wachowskis",
            poster = "poster_7",
            rating = 4.7f
        ),
        Film(
            id = 8,
            nume = "Gladiator",
            descriere = "A Roman general seeks revenge",
            durata = 155,
            gen = "Action",
            actori = "Russell Crowe",
            regizori = "Ridley Scott",
            poster = "poster_8",
            rating = 4.6f
        ),
        Film(
            id = 9,
            nume = "The Godfather",
            descriere = "Crime family saga",
            durata = 175,
            gen = "Crime",
            actori = "Marlon Brando, Al Pacino",
            regizori = "Francis Ford Coppola",
            poster = "poster_9",
            rating = 5.0f
        ),
        Film(
            id = 10,
            nume = "Fight Club",
            descriere = "An underground fight club forms",
            durata = 139,
            gen = "Drama",
            actori = "Brad Pitt, Edward Norton",
            regizori = "David Fincher",
            poster = "poster_10",
            rating = 4.6f
        )
    )
}
