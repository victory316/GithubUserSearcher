package com.example.answer.github.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.GithubRepo
import com.example.answer.github.data.source.local.GithubDao
import com.example.answer.github.data.source.remote.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GithubRepository private constructor(private val dao: GithubDao) {
    private lateinit var searchDisposable: Disposable

    fun getAllPaged(inputString: String, pageCount: Int): DataSource.Factory<Int, GithubData> {
        dao.getAll().value?.let { list ->
            if (list.isNotEmpty()) {
                return dao.getAllPaged()
            } else {

                // Gihub search query로 찾고자 하는 유저를 검색
                searchDisposable = GithubClient()
                    .getApi().searchUserForPage(inputString, pageCount, 30)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        insertList(result.getUserList())
                        searchDisposable.dispose()

                    }, { error ->
                        run {
                            error.printStackTrace()
                            searchDisposable.dispose()
                        }
                    })
            }
        }

        return dao.getAllPaged()
    }

    private fun insertList(contactList: List<GithubRepo>) {
        for (indices in contactList) {
            val githubData = GithubData(
                indices.login,
                indices.avatar_url,
                indices.score.toInt(),
                0
            )

            insert(githubData)
        }
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
