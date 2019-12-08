package com.example.answer.github

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepository
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.ConferenceData

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GithubRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<GithubData>> {
        return this.contacts
    }

    fun setupDefaultData() {
        repository.insert(GithubData("대회의실1", "image", "image", 0))
        repository.insert(GithubData("대회의실2", "image", "image", 0))
        repository.insert(GithubData("대회의실3", "image", "image", 0))
        repository.insert(GithubData("대회의실4", "image", "image", 0))
        repository.insert(GithubData("대회의실5", "image", "image", 0))
        repository.insert(GithubData("대회의실6", "image", "image", 0))
    }

    fun insert(contact: GithubData) {
        repository.insert(contact)
    }

    fun delete(contact: GithubData) {
        repository.delete(contact)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}