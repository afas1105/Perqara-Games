package com.example.perqaragames.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


@Suppress("DEPRECATED_IDENTITY_EQUALS")
abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold =
        5 // The minimum amount of items to have below your current scroll position before loading more.
    var firstVisibleItem = 0
    var findFirstCompletelyVisibleItemPosition = 0
    var findLastVisibleItemPosition = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private var current_page = 1
    private var activeAdapter = 0

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState === RecyclerView.SCROLL_STATE_IDLE) {
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
            findFirstCompletelyVisibleItemPosition =
                mLinearLayoutManager.findFirstCompletelyVisibleItemPosition()
            findLastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition()
            if (activeAdapter != firstVisibleItem) {
                onVisibleView(firstVisibleItem)
                activeAdapter = firstVisibleItem
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        findFirstCompletelyVisibleItemPosition =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition()
        findLastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached
            // Do something
            current_page++
            onLoadMore(current_page)
            loading = true
        }
    }

    abstract fun onVisibleView(position: Int)
    abstract fun onLoadMore(current_page: Int)
}