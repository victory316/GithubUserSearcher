package com.example.answer.github

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.recyclerview.GithubLikeAdapter
import com.example.answer.github.recyclerview.GithubSearchAdapter
import com.example.answer.github.room.GithubData
import com.example.answer.ui.ConferenceRoomViewModel
import com.example.answer.ui.recyclerview.ConferenceAdapter
import com.example.answer.ui.room.ConferenceData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

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
        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        githubViewModel.setupDefaultData()

        val githubSearchAdapter = GithubSearchAdapter({ githubData ->

        }, {
                githubData ->
        })

        val githubLikeAdapter = GithubLikeAdapter({ githubData ->

        }, {
                githubData ->
        })

        githubViewModel.getAll().observe(this, Observer<List<GithubData>> { githubData ->
            githubSearchAdapter.setContacts(githubData!!)
            githubLikeAdapter.setContacts(githubData)
        })

        val viewPagerAdapter = GithubViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.bottom_view_pager)
        viewPagerAdapter.setView(this)
        viewPagerAdapter.setAdapter(githubSearchAdapter, githubLikeAdapter)
        viewPager.adapter = viewPagerAdapter

        binding.topTabLayout.setupWithViewPager(viewPager)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service: GitHubService = retrofit.create<GitHubService>(GitHubService::class.java)

        val callList = service.listRepos("victory316")
        Log.d("retrofit", "callList : $callList")

        val disposable = GithubClient().getApi().getRepos(intent.extras!!.get("owner").toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> githubLikeAdapter.update(items) }
    }
}