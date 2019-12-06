package com.example.answer.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.*
import com.example.answer.R
import com.example.answer.ui.data.ConferenceRoom
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
        val jsonArray = Klaxon()
            .parseArray<ConferenceRoom>( this.assets.open("MUSINSA.json") )
//                as Array<JsonObject>
        Log.d("jsonTest", "parsed JSON : ${jsonArray}")
    }
}