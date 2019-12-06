package com.example.answer.ui.data

data class ConferenceRoom(val name: String,
                         val location: String,
                          val reservations: List<Reservations>) {

    fun getReservationList() : List<Reservations>{
        return reservations
    }
}