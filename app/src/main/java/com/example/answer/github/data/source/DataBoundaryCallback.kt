package com.example.answer.github.data.source

import androidx.paging.PagedList
import com.example.answer.github.data.GithubData
import com.example.answer.github.viewmodel.GithubViewModel
import timber.log.Timber

class DataBoundaryCallback(
    private val query: String,
    private val viewModel: GithubViewModel
) : PagedList.BoundaryCallback<GithubData>() {

    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: GithubData) {
        Timber.tag("paging").d("end loaded!")

        viewModel.doSearchByPaging()
    }
}