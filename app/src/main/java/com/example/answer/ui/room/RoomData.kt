package com.example.answer.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.answer.ui.data.Reservations

// parsing한 회의실 데이터를 넣기 위한 data class
@Entity(tableName= "room")
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "initial")
    var reservations: List<Reservations>?
) {
    constructor() : this(null, "", "", null)
}