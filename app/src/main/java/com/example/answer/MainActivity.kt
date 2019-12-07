package com.example.answer

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.answer.databinding.ActivityMainBinding
import com.example.answer.github.GithubActivity
import com.example.answer.ui.UiActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터바인딩 setup
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Status bar 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
        }

        // github 관련 페이지로 넘어가기 위한 Button의 onClickListener 설정
        binding.githubButton.setOnClickListener {
            val githubIntent = Intent(this, GithubActivity::class.java)
            startActivity(githubIntent)
        }

        // ui 관련 페이지로 넘어가기 위한 Button의 onClickListener 설정
        binding.uiButton.setOnClickListener {
            val uiIntent = Intent(this, UiActivity::class.java)
            startActivity(uiIntent)
        }
    }
}