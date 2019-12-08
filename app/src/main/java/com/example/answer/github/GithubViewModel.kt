package com.example.answer.github

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepo
import com.example.answer.github.room.GithubRepository
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.ConferenceData

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GithubRepository(application)
    private val contacts = repository.getAll()
    private lateinit var githubView: GithubActivity
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter

    fun getAll(): LiveData<List<GithubData>> {
        return this.contacts
    }

    fun setupDefaultData() {
//        repository.insert(GithubData("대회의실1", "image", "image", 0))
//        repository.insert(GithubData("대회의실2", "image", "image", 0))
//        repository.insert(GithubData("대회의실3", "image", "image", 0))
//        repository.insert(GithubData("대회의실4", "image", "image", 0))
//        repository.insert(GithubData("대회의실5", "image", "image", 0))
//        repository.insert(GithubData("대회의실6", "image", "image", 0))
    }

    fun insert(contact: GithubData) {
        repository.insert(contact)
    }

    fun insertList(contactList: List<GithubRepo>) {
        for (indices in contactList) {
            Log.d("test", "github data : $indices")
            val githubData = GithubData(indices.login, indices.avatar_url, indices.score.toInt(), 0)
            repository.insert(githubData)
        }
    }

    fun testList(contactList: GithubRepo){
        Log.d("test", "list : $contactList")
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