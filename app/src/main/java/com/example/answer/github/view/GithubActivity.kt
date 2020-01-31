package com.example.answer.github.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.data.GithubData
import com.example.answer.github.view.adapter.GithubListAdapter
import com.example.answer.github.view.adapter.GithubViewPagerAdapter
import com.example.answer.github.viewmodel.GithubViewModel
import io.reactivex.disposables.Disposable

class GithubActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGithubBinding
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter
    private lateinit var githubSearchAdapter: GithubListAdapter
    private lateinit var githubLikeAdapter: GithubListAdapter
    private var searchDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)

        setupView()
        setupViewModel()
    }

    // View 설정
    private fun setupView() {
        // 기기의 statusBar 색상을 디자인 시안에 맞게 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }

        viewPagerAdapter =
            GithubViewPagerAdapter(
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.bottom_view_pager)
        viewPager.adapter = viewPagerAdapter
        binding.topTabLayout.setupWithViewPager(viewPager)
    }

    // ViewModel 설정
    private fun setupViewModel() {
        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        githubViewModel.deleteAll()

        githubSearchAdapter = GithubListAdapter()
        githubLikeAdapter = GithubListAdapter()

        githubViewModel.getAll().observe(this, Observer<List<GithubData>> { githubData ->
            githubSearchAdapter.setContacts(githubData!!)
        })

        githubViewModel.getAllFavorites().observe(this, Observer<List<GithubData>> { githubData ->
            githubLikeAdapter.setContacts(githubData!!)
        })

        githubSearchAdapter.setViewModel(githubViewModel)
        githubLikeAdapter.setViewModel(githubViewModel)

        viewPagerAdapter.setView(this)
        viewPagerAdapter.setAdapter(githubSearchAdapter, githubLikeAdapter)

        githubViewModel.setViewPagerAdapter(viewPagerAdapter)
    }

    override fun onDestroy() {
        githubViewModel.deleteAll()
        searchDisposable?.dispose()

        super.onDestroy()
    }
}