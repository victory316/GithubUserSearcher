package com.example.answer.github.data

import androidx.paging.PageKeyedDataSource

class PagingDataSource : PageKeyedDataSource<String, GithubData>() {

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