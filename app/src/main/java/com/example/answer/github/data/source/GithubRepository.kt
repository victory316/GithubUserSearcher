package com.example.answer.github.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.source.local.GithubDao

class GithubRepository private constructor(private val dao: GithubDao) {

    fun getAllPaged(): DataSource.Factory<Int, GithubData> {
        return dao.getAllPaged()
    }

    fun insert(githubData: GithubData) {
        try {
            val thread = Thread(Runnable {
                dao.insert(githubData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun update(input: Int, name: String) {
        try {
            val thread = Thread(Runnable {
                dao.updateColumn(input, name)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun deleteAll() {
        try {
            val thread = Thread(Runnable {
                dao.deleteAll()
            })
            thread.start()
        } catch (e: Exception) { }
    }

    companion object {

        @Volatile
        private var instance: GithubRepository? = null

        fun getInstance(dao: GithubDao) =
            instance ?: synchronized(this) {
                instance ?: GithubRepository(dao).also { instance = it }
            }
    }
}
