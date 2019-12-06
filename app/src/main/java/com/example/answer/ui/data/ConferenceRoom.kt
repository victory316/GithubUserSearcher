package com.example.answer.ui.data

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

data class ConferenceRoom(val name: String, val location: String, val reservations: JsonArray<Reservations>)