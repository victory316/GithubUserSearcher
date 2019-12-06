package com.example.answer.ui.room

import androidx.room.TypeConverter
import com.example.answer.ui.data.Reservations
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ConferenceTypeConverter {
    companion object {

        val SEPARATOR = ","

//        @TypeConverter
//        @JvmStatic
//        fun resevationsToString(reservations: List<Reservations>?): String? {
//            return reservations?.map { it.startTime }?.joinToString(separator = SEPARATOR)
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun stringToReservations(reservations: String?): List<Reservations>? {
//            return reservations?.split(SEPARATOR)?.map { reservations.of(it.toInt()) }
//        }

        @TypeConverter
        @JvmStatic
        fun fromString(value: String?): List<Reservations?>? {
            val listType: Type =
                object : TypeToken<List<Reservations?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(list: List<Reservations?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }
     }
}