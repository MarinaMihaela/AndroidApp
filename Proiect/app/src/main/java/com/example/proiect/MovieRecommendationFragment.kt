package com.example.proiect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.proiect.data.db.AppDatabase
import com.example.proiect.data.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRecommendationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getDatabase(requireContext())
        val filmDao = db.filmDao()
        val container = view.findViewById<LinearLayout>(R.id.movieListContainer)

        lifecycleScope.launch {
            val sampleFilms = listOf(
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
                )
            )

            withContext(Dispatchers.IO) {
                val filmsInDb = filmDao.getAll().first()

                if (filmsInDb.isEmpty()) {
                    filmDao.insertAll(sampleFilms)
                }

                val allFilms = filmDao.getAll().first()

                withContext(Dispatchers.Main) {
                    allFilms.forEach { film ->
                        val tv = TextView(requireContext())
                        tv.text = "${film.nume} (${film.gen}) - ${film.rating ?: "-"}"
                        tv.textSize = 18f
                        container.addView(tv)
                    }
                }
            }

        }
    }
}
