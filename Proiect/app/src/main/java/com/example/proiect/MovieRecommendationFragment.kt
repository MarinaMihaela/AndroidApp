package com.example.proiect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.data.db.AppDatabase
import com.example.proiect.data.model.Film
import com.example.proiect.adapter.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRecommendationFragment : Fragment() {

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMovies)
        adapter = MovieAdapter(listOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val db = AppDatabase.getDatabase(requireContext())
        val filmDao = db.filmDao()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val films = filmDao.getAll().first()
                if (films.isEmpty()) {
                    filmDao.insertAll(
                        listOf(
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
                    )

                }
                val allFilms = filmDao.getAll().first()
                withContext(Dispatchers.Main) {
                    adapter.setMovies(allFilms)
                }
            }
        }
    }
}
