package com.example.proiect
import com.example.proiect.data.model.api.HttpHelper
import android.os.Bundle
import android.util.Log
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
import com.example.proiect.data.model.api.FilmDataSource

import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

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
                try {
                    val jsonBody = Json.encodeToString(ListSerializer(Film.serializer()), FilmDataSource.filmeInitiale)
                    val postResponse = HttpHelper.httpPostFilme("http://localhost:8080/filme", jsonBody)


                    Log.d("MainActivity", "POST /filme response: $postResponse")
                } catch (e: Exception) {
                    Log.e("MainActivity", "POST error: ${e.message}", e)
                }
                try {
                    val response = HttpHelper.httpGet("http://localhost:8080/filme")

                    val allFilms = Json.decodeFromString<List<Film>>(response)

                    android.util.Log.d("allFilms response: ", response);
                    withContext(Dispatchers.Main) {
                        adapter.setMovies(allFilms)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        android.util.Log.e("MyApp", "Eroare la parsarea filmelor: ${e.message}")
                    }
                }
            }
        }
    }
}
