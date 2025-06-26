package com.example.proiect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.adapter.MovieRatingAdapter
import com.example.proiect.viewmodel.RatedViewModel

class RatedFragment : Fragment(R.layout.fragment_rated) {

    private lateinit var adapter: MovieRatingAdapter
    private lateinit var vm: RatedViewModel

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        val rv = v.findViewById<RecyclerView>(R.id.recyclerRated)
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = MovieRatingAdapter(emptyList())
        rv.adapter = adapter

        vm = ViewModelProvider(requireActivity()).get(RatedViewModel::class.java)

        vm.getRatedForUser("currentUserId").observe(viewLifecycleOwner) { ratings ->
            adapter.setRatings(ratings)
        }
    }
}
