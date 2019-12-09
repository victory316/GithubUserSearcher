package com.example.answer.ui.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ConferenceData::class], version =1)
@TypeConverters(ConferenceTypeConverter::class)
abstract class ConferenceDatabase: RoomDatabase() {

    abstract fun conferenceDao(): ConferenceDao

    companion object {
        private var INSTANCE: ConferenceDatabase? = null

        fun getInstance(context: Context): ConferenceDatabase? {
            if (INSTANCE == null) {
                synchronized(ConferenceDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ConferenceDatabase::class.java, "room_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}