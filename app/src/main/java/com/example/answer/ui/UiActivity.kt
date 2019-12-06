package com.example.answer.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.answer.R
import com.example.answer.ui.data.ConferenceRoom
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.json.JSONObject
import java.io.IOError
import java.io.IOException
import java.io.InputStream
import java.io.StringReader
import java.util.regex.Pattern

class UiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }

        parseJson()
    }

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
        }
    }
}