package com.example.perqaragames.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perqaragames.databinding.FragmentFavoritBinding
import com.example.perqaragames.ui.GamesAdapter
import com.example.perqaragames.ui.detail.DetailActivity

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoritBinding
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        gamesAdapter = GamesAdapter {
            DetailActivity.startActivity(requireContext(), it.id ?: 0)
        }

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvGames.apply {
            adapter = gamesAdapter
            layoutManager = linearLayoutManager
        }
    }
}