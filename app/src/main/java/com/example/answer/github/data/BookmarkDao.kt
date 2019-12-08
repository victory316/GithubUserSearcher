package com.example.answer.github.data

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM Bookmark ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Bookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

}