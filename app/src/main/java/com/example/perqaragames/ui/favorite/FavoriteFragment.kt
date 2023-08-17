package com.example.perqaragames.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perqaragames.databinding.FragmentFavoritBinding
import com.example.perqaragames.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoritBinding
    private lateinit var gamesAdapter: FavoriteAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel by viewModels<FavoriteViewModel>()
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
        onGetFavorite()
    }

    private fun setupAdapter() {
        gamesAdapter = FavoriteAdapter {
            DetailActivity.startActivity(requireContext(), it.idGames ?: 0)
        }

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvGames.apply {
            adapter = gamesAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun onGetFavorite() {
        viewModel.getFavorite.observe(viewLifecycleOwner){
            gamesAdapter.setData(it)
        }
    }
}