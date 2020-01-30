package com.example.answer.github.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.github.view.adapter.GithubViewPagerAdapter
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.GithubRepo
import com.example.answer.github.data.source.GithubRepository
import com.example.answer.github.data.source.remote.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        GithubRepository(application)
    private val contacts = repository.getAll()
    private val favorites = repository.getAllFavorites()
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter
    var hideKeyboard = ObservableBoolean()

    fun doSearch(){
        val target = viewPagerAdapter.getText()

        hideKeyboard.set(true)

        viewPagerAdapter.clearText()

        // Gihub search query로 찾고자 하는 유저를 검색
        val searchDisposable = GithubClient()
            .getApi().searchUser(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                insertList(result.getUserList())

            }, {
                    error ->
                run {
                    error.printStackTrace()
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