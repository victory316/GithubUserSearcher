package com.example.answer.github

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.answer.R
import com.example.answer.databinding.ActivityGithubBinding
import com.example.answer.github.data.UserList
import com.example.answer.github.recyclerview.GithubLikeAdapter
import com.example.answer.github.recyclerview.GithubSearchAdapter
import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepo
import com.example.answer.ui.ConferenceRoomViewModel
import com.example.answer.ui.recyclerview.ConferenceAdapter
import com.example.answer.ui.room.ConferenceData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGithubBinding
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var viewPagerAdapter: GithubViewPagerAdapter
    private lateinit var dataList: List<GithubData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        // 기기의 statusBar 색상을 디자인 시안에 맞게 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        Log.d("networkTest", "network status : $isConnected")

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

        githubLikeAdapter.setView(this)
        githubSearchAdapter.setView(this)

        githubViewModel.getAll().observe(this, Observer<List<GithubData>> { githubData ->
            githubSearchAdapter.setContacts(githubData!!)
            githubLikeAdapter.setContacts(githubData)
        })

        githubViewModel.setView(this)
        viewPagerAdapter = GithubViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.bottom_view_pager)
        viewPagerAdapter.setView(this)
        viewPagerAdapter.setAdapter(githubSearchAdapter, githubLikeAdapter)
        viewPager.adapter = viewPagerAdapter

        binding.topTabLayout.setupWithViewPager(viewPager)
        githubViewModel.setViewPagerAdapter(viewPagerAdapter)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        val service: GitHubService = retrofit.create<GitHubService>(GitHubService::class.java)

//        val callList = service.listRepos("victory316")
////        Log.d("retrofit", "callList : $callList")

//        val disposable = GithubClient().getApi().getRepos(intent.extras!!.get("victory316").toString())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { items -> githubLikeAdapter.update(items) }

        val searchDisposable = GithubClient().getApi().getRepos("victory316")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> githubViewModel.insertList(items) }

        val likeDisposable = GithubClient().getApi().getRepos("victory316")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> githubViewModel.insertList(items) }
    }

    fun doSearch(){

        val target = viewPagerAdapter.getText()

        viewPagerAdapter.clearText()

        Log.d("test", "target : $target")

        val disposable = GithubClient().getApi().searchUser("user:$target")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                for(indices in result.getUserList()) {
                    Log.d("test", "this is it! : $indices")
                }
            }, {
                error ->
                run {
                    error.printStackTrace()
                    Toast.makeText(this, "결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}