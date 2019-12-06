package com.example.answer.ui.room

import androidx.room.TypeConverter
import com.example.answer.ui.data.Reservations
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ConferenceTypeConverter {
    companion object {

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