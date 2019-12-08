package com.example.answer.ui.room

import android.app.Application
import androidx.lifecycle.LiveData

class ConferenceRepository(application: Application) {

    private val conferenceDatabase = ConferenceDatabase.getInstance(application)!!
    private val conferenceDao: ConferenceDao = conferenceDatabase.conferenceDao()
    private val conferenceData: LiveData<List<ConferenceData>> = conferenceDao.getAll()

    fun getAll(): LiveData<List<ConferenceData>> {
        return conferenceData
    }

    fun insert(conferenceData: ConferenceData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.insert(conferenceData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(conferenceData: ConferenceData) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.delete(conferenceData)
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

    fun updateFull(name: String, input: Int) {
        try {
            val thread = Thread(Runnable {
                conferenceDao.updateFull(name, input)
            })
            thread.start()
        } catch (e: Exception) { }
    }
}
