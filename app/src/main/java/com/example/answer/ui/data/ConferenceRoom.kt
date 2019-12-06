package com.example.answer.ui.data

import com.google.gson.JsonArray

data class ConferenceRoom(val name: String,
                         val location: String,
                          val reservations: List<Reservations>)