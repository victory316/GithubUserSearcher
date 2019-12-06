package com.example.answer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UiViewModel {
    private val _selectedID = MutableLiveData<Int>()
    val selectedID: LiveData<Int> = _selectedID

    init {
        _selectedID.value = 1
    }

    fun toggle(optionID: Int) {
        _selectedID.value = optionID
    }
}