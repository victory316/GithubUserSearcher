package com.example.answer.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.ActivityUiBinding
import com.example.answer.ui.recyclerview.ConferenceAdapter
import com.example.answer.ui.recyclerview.ConferenceMiniAdapter
import com.example.answer.ui.room.ConferenceData
import com.google.gson.Gson
import java.io.FileNotFoundException

class UiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUiBinding
    private lateinit var conferenceViewModel: ConferenceViewModel
    private lateinit var dataList: List<ConferenceData>
    private lateinit var conferenceAdapter: ConferenceAdapter
    private lateinit var conferenceMiniAdapter: ConferenceMiniAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ui)
        setupView()
        setupViewModel()
    }

    // View 설정
    private fun setupView(){
        conferenceAdapter = ConferenceAdapter()
        conferenceMiniAdapter = ConferenceMiniAdapter()

        val roomDetailLayoutManager = LinearLayoutManager(this)
        val roomMiniLayoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)

        binding.roomDetailRecyclerView.adapter = conferenceAdapter
        binding.roomDetailRecyclerView.layoutManager = roomDetailLayoutManager
        binding.roomDetailRecyclerView.setHasFixedSize(true)

        binding.availableRoomRecyclerView.adapter = conferenceMiniAdapter
        binding.availableRoomRecyclerView.layoutManager = roomMiniLayoutManager
        binding.availableRoomRecyclerView.setHasFixedSize(true)

        binding.button01.isChecked = true

        // 기기의 statusBar 색상을 디자인 시안에 맞게 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }
    }

    // ViewModel 설정
    private fun setupViewModel() {
        conferenceViewModel = ViewModelProviders.of(this).get(ConferenceViewModel::class.java)
        conferenceViewModel.setupDefaultData()
        dataList = parseJson()

        for (indices in dataList) {
            conferenceViewModel.insert(indices)
        }

        conferenceViewModel.getAll().observe(this, Observer<List<ConferenceData>> { roomData ->
            conferenceAdapter.setContacts(roomData!!)
        })

        conferenceViewModel.getAllAvailable().observe(this, Observer<List<ConferenceData>> { roomData ->
            binding.conferenceCapacityText.text = roomData.size.toString()
            conferenceMiniAdapter.setContacts(roomData!!)
        })

        conferenceAdapter.setViewModel(conferenceViewModel)
    }

    // MUSINSA.json으로부터 json string을 parse
    // Parsing된 string으로부터 ConferenceRoom List를 생성
    private fun parseJson(): List<ConferenceData> {
        val gson = Gson()
        val testString: String

        // 파일 입력에 오류가 있을 경우의 예외처리
       try {
            testString = applicationContext.assets.open("MUSINSA.json").bufferedReader().use {it.readText()}
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return emptyList()
        }

        return gson.fromJson(testString, Array<ConferenceData>::class.java).toList()
    }
}