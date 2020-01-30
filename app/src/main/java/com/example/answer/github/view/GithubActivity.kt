package com.example.answer.github.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.data.source.remote.GithubClient
import com.example.answer.github.data.GithubData
import com.example.answer.github.viewmodel.GithubViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GithubActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGithubBinding
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter
    private lateinit var githubSearchAdapter: GithubSearchAdapter
    private lateinit var githubLikeAdapter: GithubLikeAdapter
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

        viewPagerAdapter = GithubViewPagerAdapter(
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

        githubSearchAdapter =
            GithubSearchAdapter()
        githubLikeAdapter = GithubLikeAdapter()
        githubLikeAdapter.setView(this)
        githubSearchAdapter.setView(this)

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

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Activity.hideKeyboard() {
        if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
    }

    override fun onDestroy() {
        githubViewModel.deleteAll()
        searchDisposable?.dispose()

        super.onDestroy()
    }
}