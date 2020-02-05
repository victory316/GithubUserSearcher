package com.example.answer.github.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.answer.github.data.GithubData


// Room DB 구현에 필요한 DAO 클래스
@Dao
interface GithubDao {

    @Query("SELECT * FROM github ORDER BY name ASC")
    fun getAll(): LiveData<List<GithubData>>

    @Query("SELECT * FROM github WHERE favorite = 1 ORDER BY name ASC ")
    fun getAllFavorites(): LiveData<List<GithubData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(githubData: GithubData)

    @Delete
    fun delete(githubData: GithubData)

    @Query("DELETE FROM github WHERE favorite = 0")
    fun deleteAll()

    @Query("UPDATE github SET favorite = (:input) WHERE name = (:name)")
    fun updateColumn(input: Int, name: String)

    @Query("SELECT * FROM github")
    fun getAllPaged(): DataSource.Factory<Int, GithubData>
}