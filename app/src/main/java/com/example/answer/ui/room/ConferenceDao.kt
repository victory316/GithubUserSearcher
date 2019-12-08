package com.example.answer.ui.room

import androidx.lifecycle.LiveData
import androidx.room.*


// Room DB 구현에 필요한 DAO 클래스
@Dao
interface ConferenceDao {

    @Query("SELECT * FROM room ORDER BY name ASC")
    fun getAll(): LiveData<List<ConferenceData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(conferenceData: ConferenceData)

    @Delete
    fun delete(conferenceData: ConferenceData)

    @Query("DELETE FROM room")
    fun deleteAll()
}