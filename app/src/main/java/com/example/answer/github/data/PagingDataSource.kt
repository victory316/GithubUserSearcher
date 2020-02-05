package com.example.answer.github.data

import androidx.paging.PageKeyedDataSource
import com.example.answer.github.data.source.remote.GithubApi

// TODO 현재 사용되고 있지 않으며, 관련해서 추후 리뷰 필요.
// https://github.com/android/architecture-components-samples/tree/master/PagingWithNetworkSample 참조할 것.

class PagingDataSource (
    private val api: GithubApi,
    private val subredditName: String
) : PageKeyedDataSource<String, GithubData>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GithubData>) {

        TODO("not implemented")
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GithubData>) {

        TODO("not implemented")
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GithubData>) {

        TODO("not implemented")
    }
}