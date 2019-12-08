package com.example.answer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.ConferenceData

class ConferenceRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConferenceRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<ConferenceData>> {
        return this.contacts
    }

    // 최초 회의실 설정을 위해 우선적으로 dummy 데이터를 insert
    fun setupDefaultData() {
        repository.insert(ConferenceData("대회의실1", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실1", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실2", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실3", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실4", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실5", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실6", "MUSINSA 1 / B1", null))
        repository.insert(ConferenceData("회의실7", "MUSINSA 1 / B1", null))
    }

    fun insert(contact: ConferenceData) {
        repository.insert(contact)
    }

    fun delete(contact: ConferenceData) {
        repository.delete(contact)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}