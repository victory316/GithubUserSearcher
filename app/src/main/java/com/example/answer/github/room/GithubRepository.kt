package com.example.answer.github.room

import android.app.Application
import androidx.lifecycle.LiveData

class GithubRepository(application: Application) {

    private val githubDatabase = GithubDatabase.getInstance(application)!!
    private val githubDao: GithubDao = githubDatabase.conferenceDao()
    private val githubData: LiveData<List<GithubData>> = githubDao.getAll()

    fun getAll(): LiveData<List<GithubData>> {
        return githubData
    }

    fun insert(githubData: GithubData) {
        try {
            val thread = Thread(Runnable {
                githubDao.insert(githubData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(githubData: GithubData) {
        try {
            val thread = Thread(Runnable {
                githubDao.delete(githubData)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun update(input: Int, name: String) {
        try {
            val thread = Thread(Runnable {
                githubDao.updateColumn(input, name)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun deleteAll() {
        try {
            val thread = Thread(Runnable {
                githubDao.deleteAll()
            })
            thread.start()
        } catch (e: Exception) { }
    }
}
