package com.example.answer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.RoomData

class ConferenceRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConferenceRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<RoomData>> {
        return this.contacts
    }

    fun insert(contact: RoomData) {
        repository.insert(contact)
    }

    fun delete(contact: RoomData) {
        repository.delete(contact)
    }
}