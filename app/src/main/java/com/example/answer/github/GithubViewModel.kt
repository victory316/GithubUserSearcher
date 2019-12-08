package com.example.answer.github

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepo
import com.example.answer.github.room.GithubRepository

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GithubRepository(application)
    private val contacts = repository.getAll()
    private val favorites = repository.getAllFavorites()
    private lateinit var githubView: GithubActivity
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter

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
            val githubData = GithubData(indices.login, indices.avatar_url, indices.score.toInt(), 0)
            repository.insert(githubData)
        }
    }

    fun updateList(input: Int, name: String) {
        repository.update(input, name)
    }

    fun doSearch() {
        githubView.doSearch()
    }

    fun clearText() {
        viewPagerAdapter.clearText()
    }

    fun setView(view: GithubActivity) {
        githubView = view
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