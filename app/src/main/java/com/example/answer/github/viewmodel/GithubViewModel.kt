package com.example.answer.github.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.GithubRepo
import com.example.answer.github.data.source.DataBoundaryCallback
import com.example.answer.github.data.source.GithubRepository
import com.example.answer.github.data.source.remote.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GithubViewModel internal constructor(
    private val repository: GithubRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var doShimmerAnimation: ObservableBoolean = ObservableBoolean()

    private var personsLiveData: LiveData<PagedList<GithubData>>
    private var pageCount = 1

    val _searchString = MutableLiveData<String>()
    val searchString: LiveData<String>
        get() = _searchString

    private var factory: DataSource.Factory<Int, GithubData> =
        repository.getAllPaged("", pageCount)

    init {
        // 실행 시점에서의 DB 초기화
        repository.deleteAll()

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()

        val boundaryCallback =
            DataBoundaryCallback("", this)

        val pagedListBuilder: LivePagedListBuilder<Int, GithubData> =
            LivePagedListBuilder<Int, GithubData>(
                factory,
                config
            ).setBoundaryCallback(boundaryCallback)

        personsLiveData = pagedListBuilder.build()
    }

    private fun doOnNewSearch() {
        Timber.tag("paging").d("doOnNewSearch : $pageCount")

        pageCount = 1

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()

        val boundaryCallback =
            DataBoundaryCallback("", this)

        val pagedListBuilder: LivePagedListBuilder<Int, GithubData> =
            LivePagedListBuilder<Int, GithubData>(
                factory,
                config
            ).setBoundaryCallback(boundaryCallback)

        personsLiveData = pagedListBuilder.build()
    }

    fun getPersonsLiveData() = personsLiveData

    fun doSearch() {

        deleteAll()
//        doShimmerAnimation.set(true)

        doOnNewSearch()
        repository.getSearchedList("a")
    }

    fun insert(contact: GithubData) {
        repository.insert(contact)
    }

    fun updateList(input: Int, name: String) {
        repository.update(input, name)
    }

    fun clearText() {
        _searchString.postValue("")
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}