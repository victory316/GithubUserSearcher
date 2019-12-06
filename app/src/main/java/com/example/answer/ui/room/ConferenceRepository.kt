package com.example.answer.ui.room

import android.app.Application
import androidx.lifecycle.LiveData

class ConferenceRepository(application: Application) {

    private val conferenceDatabase = ConferenceRoomDatabase.getInstance(application)!!
    private val conferenceDao: ConferenceDao = conferenceDatabase.conferenceDao()
    private val conferenceRoomData: LiveData<List<ConferenceRoomData>> = conferenceDao.getAll()

    fun getAll(): LiveData<List<ConferenceRoomData>> {
        return conferenceRoomData
    }

    fun insert(conferenceRoomData: ConferenceRoomData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.insert(conferenceRoomData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(conferenceRoomData: ConferenceRoomData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.delete(conferenceRoomData)
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
