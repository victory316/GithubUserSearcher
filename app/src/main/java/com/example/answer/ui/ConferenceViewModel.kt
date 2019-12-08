package com.example.answer.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.answer.ui.room.ConferenceRepository
import com.example.answer.ui.room.ConferenceData

class ConferenceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ConferenceRepository(application)
    private val contacts = repository.getAll()
    private val miniContacts = repository.getAllAvaliable()


    fun getAll(): LiveData<List<ConferenceData>> {
        return this.contacts
    }

    fun getAllAvailable(): LiveData<List<ConferenceData>> {
        return this.miniContacts
    }

    // 최초 회의실 설정을 위해 우선적으로 dummy 데이터를 insert
    fun setupDefaultData() {
        repository.insert(ConferenceData("대회의실1", "MUSINSA 1 / B1", null,0))
        repository.insert(ConferenceData("회의실1", "MUSINSA 1 / B1", null,0))
        repository.insert(ConferenceData("회의실2", "MUSINSA 1 / B1", null,0))
        repository.insert(ConferenceData("회의실3", "MUSINSA 1 / B1", null, 0))
        repository.insert(ConferenceData("회의실4", "MUSINSA 1 / B1", null, 0))
        repository.insert(ConferenceData("회의실5", "MUSINSA 1 / B1", null, 0))
        repository.insert(ConferenceData("회의실6", "MUSINSA 1 / B1", null, 0))
        repository.insert(ConferenceData("회의실7", "MUSINSA 1 / B1", null, 0))
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

    fun updateFull(name: String, input: Int){
        repository.updateFull(name, input)
    }
}