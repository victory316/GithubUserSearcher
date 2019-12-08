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