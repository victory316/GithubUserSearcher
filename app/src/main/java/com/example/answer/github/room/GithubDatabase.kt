package com.example.answer.github.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [GithubData::class], version =1)
abstract class GithubDatabase: RoomDatabase() {

    abstract fun conferenceDao(): GithubDao

    companion object {
        private var INSTANCE: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase? {
            if (INSTANCE == null) {
                synchronized(GithubDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GithubDatabase::class.java, "github_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}