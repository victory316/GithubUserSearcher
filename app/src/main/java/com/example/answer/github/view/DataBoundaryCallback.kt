package com.example.answer.github.view

import android.util.Log
import androidx.paging.PagedList
import com.example.answer.github.data.GithubData
import com.example.answer.github.viewmodel.GithubViewModel

class DataBoundaryCallback(
    private val query: String,
    private val viewModel: GithubViewModel
) : PagedList.BoundaryCallback<GithubData>() {

    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: GithubData) {
        Log.d("pagingTest","onItemAtEndLoaded")
    }
}