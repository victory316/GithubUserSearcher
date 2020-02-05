package com.example.answer.github.data.source

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
        viewModel.doSearchByPaging()
    }
}