package com.example.answer.github.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.answer.ui.data.Reservations
import com.google.gson.annotations.SerializedName

// parsing한 회의실 데이터를 넣기 위한 data class
@Entity(tableName= "github")
data class GithubData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "score")
    var location: String,

    @ColumnInfo(name = "like")
    var like: Int
) {
//    constructor() : this(null, "", "", null)
}