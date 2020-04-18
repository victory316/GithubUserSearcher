package com.example.answer.github.viewmodel

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.answer.github.ui.GithubViewPagerAdapter
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.GithubRepo
import com.example.answer.github.data.source.GithubRepository
import com.example.answer.github.data.source.remote.GithubClient
import com.example.answer.github.data.source.DataBoundaryCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubViewModel internal constructor(
    private val repository: GithubRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val contacts = repository.getAll()
    private val favorites = repository.getAllFavorites()
//    private lateinit var viewPagerAdapter: GithubViewPagerAdapter

    var doShimmerAnimation: ObservableBoolean = ObservableBoolean()

    private var personsLiveData: LiveData<PagedList<GithubData>>
    private var factory: DataSource.Factory<Int, GithubData> =
        repository.getAllPaged()

    private var pageCount = 1

    var _searchString = MutableLiveData<String>()
    val searchString: LiveData<String>
        get() = _searchString

    val testString = ObservableField<String>()

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

        doShimmerAnimation.set(true)

        doOnNewSearch()

        // Gihub search query로 찾고자 하는 유저를 검색
        val searchDisposable = GithubClient()
            .getApi().searchUserForPage(searchString.value!!, pageCount, 30)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                insertList(result.getUserList())
//                personsLiveData

                doShimmerAnimation.set(false)

            }, { error ->
                run {
                    error.printStackTrace()
                    doShimmerAnimation.set(false)
                }
            })
    }

    private var searchOnProgress = false

    fun doSearchByPaging() {

        if (!searchOnProgress) {
            searchOnProgress = true
//            val target = viewPagerAdapter.getText()
            doShimmerAnimation.set(true)

            // Gihub search query로 찾고자 하는 유저를 검색
            val searchDisposable = GithubClient()
                .getApi().searchUserForPage("", pageCount, 30)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    insertList(result.getUserList())
//                personsLiveData

                    doShimmerAnimation.set(false)
                    searchOnProgress = false
                }, { error ->
                    run {
                        error.printStackTrace()
                        doShimmerAnimation.set(false)
                        searchOnProgress = false
                    }
                })

            pageCount++
        }
    }

    fun getAll(): LiveData<List<GithubData>> {
        return this.contacts
    }

    fun getAllFavorites(): LiveData<List<GithubData>> {
        return this.favorites
    }

    fun insert(contact: GithubData) {
        repository.insert(contact)
    }

    fun insertList(contactList: List<GithubRepo>) {
        for (indices in contactList) {
            val githubData = GithubData(
                indices.login,
                indices.avatar_url,
                indices.score.toInt(),
                0
            )
            repository.insert(githubData)
        }
    }

    fun updateList(input: Int, name: String) {
        repository.update(input, name)
    }

    fun clearText() {
        _searchString.postValue("")
    }

    fun delete(contact: GithubData) {
        repository.delete(contact)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}