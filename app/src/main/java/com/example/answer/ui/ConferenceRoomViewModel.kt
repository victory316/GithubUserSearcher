package com.example.answer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.ConferenceRoomData

class ConferenceRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConferenceRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<ConferenceRoomData>> {
        return this.contacts
    }

    fun setupDefaultData() {
        repository.insert(ConferenceRoomData("대회의실1", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실1", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실2", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실3", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실4", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실5", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실6", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceRoomData("회의실7", "MUSINSA 1 / B1", null))
    }

    fun insert(contact: ConferenceRoomData) {
        repository.insert(contact)
    }

    fun delete(contact: ConferenceRoomData) {
        repository.delete(contact)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}