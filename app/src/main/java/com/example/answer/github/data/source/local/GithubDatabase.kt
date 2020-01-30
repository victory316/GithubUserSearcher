package com.example.answer.github.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.answer.github.data.FavoriteData
import com.example.answer.github.data.GithubData

@Database(entities = [GithubData::class, FavoriteData::class], version =2)
abstract class GithubDatabase: RoomDatabase() {

    abstract fun githubDao(): GithubDao

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