package com.example.answer.ui.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ConferenceRoomData::class], version =1)
@TypeConverters(ConferenceTypeConverter::class)
abstract class ConferenceRoomDatabase: RoomDatabase() {

    abstract fun conferenceDao(): ConferenceDao

    companion object {
        private var INSTANCE: ConferenceRoomDatabase? = null

        fun getInstance(context: Context): ConferenceRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ConferenceRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ConferenceRoomDatabase::class.java, "room_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}