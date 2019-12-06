package com.example.answer.ui.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ConferenceRoomDatabase: RoomDatabase() {

    abstract fun conferenceDao(): ConferenceDao

    companion object {
        private var INSTANCE: ConferenceRoomDatabase? = null

        fun getInstance(context: Context): ConferenceRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ConferenceRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ConferenceRoomDatabase::class.java, "room")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}