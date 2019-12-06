package com.example.answer.ui.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.answer.ui.data.Reservations

class ConferenceRepository(application: Application) {

    private val conferenceDatabase = ConferenceRoomDatabase.getInstance(application)!!
    private val conferenceDao: ConferenceDao = conferenceDatabase.conferenceDao()
    private val roomData: LiveData<List<RoomData>> = conferenceDao.getAll()

    fun getAll(): LiveData<List<RoomData>> {
        return roomData
    }

    fun insert(roomData: RoomData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.insert(roomData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(roomData: RoomData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.delete(roomData)
            })
            thread.start()
        } catch (e: Exception) { }
    }
}
