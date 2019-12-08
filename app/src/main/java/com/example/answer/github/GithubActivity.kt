package com.example.answer.github

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.room.GithubData
import com.google.android.material.tabs.TabLayout


class GithubActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGithubBinding
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var dataList: List<GithubData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        // 기기의 statusBar 색상을 디자인 시안에 맞게 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)

        val viewPagerAdapter = GithubViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.bottom_view_pager)
        viewPager.adapter = viewPagerAdapter

        binding.topTabLayout.setupWithViewPager(viewPager)
    }
}