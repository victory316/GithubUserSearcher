package com.example.answer.github.room

import androidx.lifecycle.LiveData
import androidx.room.*


// Room DB 구현에 필요한 DAO 클래스
@Dao
interface GithubDao {

    @Query("SELECT * FROM github ORDER BY name ASC")
    fun getAll(): LiveData<List<GithubData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(githubData: GithubData)

    @Delete
    fun delete(githubData: GithubData)

    @Query("DELETE FROM github")
    fun deleteAll()
}