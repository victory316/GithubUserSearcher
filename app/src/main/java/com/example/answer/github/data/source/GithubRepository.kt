package com.example.answer.github.data.source

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.answer.github.data.source.local.GithubDao
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.source.local.GithubDatabase

class GithubRepository(application: Application) {

    private val githubDatabase = GithubDatabase.getInstance(
        application
    )!!
    private val githubDao: GithubDao = githubDatabase.githubDao()
    private val githubData: LiveData<List<GithubData>> = githubDao.getAll()
    private val favoriteData: LiveData<List<GithubData>> = githubDao.getAllFavorites()

    fun getAll(): LiveData<List<GithubData>> {
        return githubData
    }

    fun getAllFavorites(): LiveData<List<GithubData>> {
        return favoriteData
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
