package com.example.answer.github.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.data.GithubData
import com.example.answer.github.ui.GithubListAdapter
import com.example.answer.github.ui.GithubViewPagerAdapter
import com.example.answer.github.ui.PagingAdapter
import com.example.answer.github.util.InjectorUtils
import com.example.answer.github.viewmodel.GithubViewModel
import io.reactivex.disposables.Disposable

class GithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubBinding
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter
    private lateinit var githubSearchAdapter: GithubListAdapter
    private lateinit var githubLikeAdapter: GithubListAdapter
    private lateinit var pagingAdapter: PagingAdapter
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
        githubSearchAdapter = GithubListAdapter()
        githubLikeAdapter = GithubListAdapter()

        viewPagerAdapter.setView(this)
        pagingAdapter = PagingAdapter(this)

    }

    override fun onDestroy() {
        searchDisposable?.dispose()

        super.onDestroy()
    }
}