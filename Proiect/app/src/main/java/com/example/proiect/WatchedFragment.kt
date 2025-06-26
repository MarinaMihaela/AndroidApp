package com.example.proiect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.adapter.MovieAdapter
import com.example.proiect.viewmodel.VizionateViewModel

class WatchedFragment : Fragment(R.layout.fragment_watched) {
    private lateinit var adapter: MovieAdapter
    private lateinit var vm: VizionateViewModel

    override fun onViewCreated(v: View, saved: Bundle?) {
        super.onViewCreated(v, saved)

        // 1) set up RecyclerView + adapter
        adapter = MovieAdapter(emptyList())
        val rv = v.findViewById<RecyclerView>(R.id.recyclerWatched)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        // 2) obtain your ViewModel via the AndroidViewModelFactory
        vm = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[VizionateViewModel::class.java]

        // 3) observe the Flow-backed LiveData
        vm.getWatchedForUser("CURRENT_USER_ID")
            .observe(viewLifecycleOwner) { filmList ->
                adapter.setMovies(filmList)
            }

    }
}
