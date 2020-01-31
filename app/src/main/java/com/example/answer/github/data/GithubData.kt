package com.example.answer.github.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// parsing한 회의실 데이터를 넣기 위한 data class
@Entity(tableName= "github")
data class GithubData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Int
)