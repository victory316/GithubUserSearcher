package com.example.answer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UiViewModel {
    private val _selectedID = MutableLiveData<Int>()

    init {
        _selectedID.value = 1
    }

}