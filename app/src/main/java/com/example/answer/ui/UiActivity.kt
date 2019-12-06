package com.example.answer.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.ActivityUiBinding
import com.example.answer.ui.data.ConferenceRoom
import com.example.answer.ui.data.Reservations
import com.example.answer.ui.recyclerview.ConferenceAdapter
import com.example.answer.ui.room.ConferenceRoomData
import com.example.answer.ui.room.ConferenceRoomDatabase
import com.google.gson.Gson

class UiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUiBinding
    private lateinit var cRoomViewModel: ConferenceRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ui)

        val adapter = ConferenceAdapter({ roomData ->

        }, {
            roomData ->
        })

        val linearLayoutManager = LinearLayoutManager(this)

        binding.availableRoomView.adapter = adapter
        binding.availableRoomView.layoutManager = linearLayoutManager
        binding.availableRoomView.setHasFixedSize(true)

        // ViewModel의 설정
        cRoomViewModel = ViewModelProviders.of(this).get(ConferenceRoomViewModel::class.java)

        val tempReservation = Reservations("100", "1000")
        val tempReservationList = ArrayList<Reservations>()
        tempReservationList.add(tempReservation)
        val testData = ConferenceRoomData(1,"아하","여기", tempReservationList)
        cRoomViewModel.insert(testData)
//
        cRoomViewModel.getAll().observe(this, Observer<List<ConferenceRoomData>> { roomData ->
            adapter.setContacts(roomData!!)
        })

        // 기기의 statusBar 색상을 디자인 시안에 맞게 변경
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }

        parseJson()
    }


    // MUSINSA.json으로부터 json string을 parse
    // Parsing된 string으로부터 ConferenceRoom List를 생성
    private fun parseJson() {
        val gson = Gson()
        val testString = applicationContext.assets.open("MUSINSA.json").bufferedReader().use {it.readText()}
        val testArray = gson.fromJson(testString, Array<ConferenceRoom>::class.java).toList()
        Log.d("jsonTest", "parsed String : $testString")

        for(indices in testArray) {
            Log.d("jsonTest" , "$indices")
            Log.d("jsonTest", "!!reservations : ${indices.reservations}")

            val testReservation = indices.reservations

            for(indices in testReservation) {
                Log.d("jsonTest", "starttime : ${indices.startTime}")
                Log.d("jsonTest", "endtime : ${indices.endTime}")
            }

            for(indices in indices.getReservationList()) {
                Log.d("jsonTest", "starttime : ${indices.startTime}")
                Log.d("jsonTest", "endtime : ${indices.endTime}")
            }
        }
    }
}