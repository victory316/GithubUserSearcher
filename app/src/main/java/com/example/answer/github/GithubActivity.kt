package com.example.answer.github

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
import com.example.answer.github.recyclerview.GithubLikeAdapter
import com.example.answer.github.recyclerview.GithubSearchAdapter
import com.example.answer.github.room.GithubData
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
        setContentView(R.layout.activity_ui)
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

        viewPagerAdapter = GithubViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.bottom_view_pager)
        viewPager.adapter = viewPagerAdapter
        binding.topTabLayout.setupWithViewPager(viewPager)
    }

    // ViewModel 설
    private fun setupViewModel() {
        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        githubViewModel.deleteAll()

        githubSearchAdapter = GithubSearchAdapter()
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

        githubViewModel.setView(this)
        githubViewModel.setViewPagerAdapter(viewPagerAdapter)
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Activity.hideKeyboard() {
        if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
    }

    fun doSearch(){
        hideKeyboard()

        val target = viewPagerAdapter.getText()

        viewPagerAdapter.clearText()

        searchDisposable = GithubClient().getApi().searchUser(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                githubViewModel.insertList(result.getUserList())

            }, {
                error ->
                run {
                    error.printStackTrace()
                    Toast.makeText(this, "결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            })

        githubViewModel.deleteAll()
    }

    override fun onDestroy() {
        githubViewModel.deleteAll()

        super.onDestroy()
    }
}