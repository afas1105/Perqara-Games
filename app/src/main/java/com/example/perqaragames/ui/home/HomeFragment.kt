package com.example.perqaragames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perqaragames.databinding.FragmentHomeBinding
import com.example.perqaragames.model.ResultData
import com.example.perqaragames.ui.detail.DetailActivity
import com.example.perqaragames.utils.EndlessRecyclerOnScrollListener

class HomeFragment : Fragment(), HomeView {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val presenter by lazy { HomePresenter(this) }

    private var dataGames: MutableList<ResultData>? = mutableListOf()
    private var scrollListener: EndlessRecyclerOnScrollListener? = null
    private var searchText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        presenter.getData(page = 1, search = "")
        binding.svGames.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchText = query
                presenter.getData(1, pageSize = 100, search = query.toString())
                dataGames?.clear()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchText = newText
                presenter.getData(1, pageSize = 100, search = newText.toString())
                dataGames?.clear()
                return true
            }
        })
    }

    override fun onGetGames(data: List<ResultData>?) {
        data?.let { dataGames?.addAll(it) }
        dataGames?.let { gamesAdapter.setData(it) }
    }

    private fun setupAdapter() {
        gamesAdapter = GamesAdapter {
            DetailActivity.startActivity(requireContext(), it.id ?: 0)
        }

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvGames.apply {
            adapter = gamesAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)

            scrollListener = object : EndlessRecyclerOnScrollListener(linearLayoutManager) {
                override fun onVisibleView(position: Int) {}

                override fun onLoadMore(current_page: Int) {
                    presenter.getData(page = current_page, search = searchText.toString())
                }
            }

            addOnScrollListener(scrollListener as EndlessRecyclerOnScrollListener)
        }
    }
}