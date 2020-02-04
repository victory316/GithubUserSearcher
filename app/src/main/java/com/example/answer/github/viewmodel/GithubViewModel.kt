package com.example.answer.github.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.answer.github.view.adapter.GithubViewPagerAdapter
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.GithubRepo
import com.example.answer.github.data.source.GithubRepository
import com.example.answer.github.data.source.local.GithubDatabase
import com.example.answer.github.data.source.remote.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        GithubRepository(application)
    private val contacts = repository.getAll()
    private val favorites = repository.getAllFavorites()
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter

    var doShimmerAnimation: ObservableBoolean = ObservableBoolean()

    private var personsLiveData: LiveData<PagedList<GithubData>>

    init {
        val factory: DataSource.Factory<Int, GithubData> =
            GithubDatabase.getInstance(application)!!.githubDao().getAllPaged()

        val pagedListBuilder: LivePagedListBuilder<Int, GithubData> = LivePagedListBuilder<Int, GithubData>(factory,
            100)
        personsLiveData = pagedListBuilder.build()
    }

    fun getPersonsLiveData() = personsLiveData

    fun doSearch(){
        val target = viewPagerAdapter.getText()

        viewPagerAdapter.clearText()
        doShimmerAnimation.set(true)

        // Gihub search query로 찾고자 하는 유저를 검색
        val searchDisposable = GithubClient()
            .getApi().searchUser(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                insertList(result.getUserList())

                doShimmerAnimation.set(false)

            }, {
                    error ->
                run {
                    error.printStackTrace()
                    doShimmerAnimation.set(false)
                }
            })

        deleteAll()
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
        viewPagerAdapter.clearText()
    }

    fun setViewPagerAdapter(adapter: GithubViewPagerAdapter) {
        viewPagerAdapter = adapter
    }

    fun delete(contact: GithubData) {
        repository.delete(contact)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}