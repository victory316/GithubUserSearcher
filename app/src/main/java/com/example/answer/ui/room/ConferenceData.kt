package com.example.answer.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.answer.ui.data.Reservations
import com.google.gson.annotations.SerializedName

// parsing한 회의실 데이터를 넣기 위한 data class
@Entity(tableName= "room")
data class ConferenceData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "initial")
    @TypeConverters(ConferenceTypeConverter::class)
    @SerializedName("reservations")
    var reservations: List<Reservations>?,

    @ColumnInfo(name = "is_full")
    var is_full: Int
)