package com.example.answer.github.room

import android.app.Application
import androidx.lifecycle.LiveData

class GithubRepository(application: Application) {

    private val conferenceDatabase = GithubDatabase.getInstance(application)!!
    private val conferenceDao: GithubDao = conferenceDatabase.conferenceDao()
    private val conferenceData: LiveData<List<GithubData>> = conferenceDao.getAll()

    fun getAll(): LiveData<List<GithubData>> {
        return conferenceData
    }

    fun insert(conferenceData: GithubData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.insert(conferenceData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(conferenceData: GithubData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.delete(conferenceData)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun deleteAll() {
        try {
            val thread = Thread(Runnable {
                conferenceDao.deleteAll()
            })
            thread.start()
        } catch (e: Exception) { }
    }
}
