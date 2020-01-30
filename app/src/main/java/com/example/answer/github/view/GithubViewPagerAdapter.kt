package com.example.answer.github.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.answer.github.fragments.LikeFragment
import com.example.answer.github.fragments.SearchFragment

class GithubViewPagerAdapter(
    fm: FragmentManager?
) :
    FragmentPagerAdapter(fm!!) {
    private lateinit var view: GithubActivity
    private lateinit var searchAdapter: GithubSearchAdapter
    private lateinit var likeAdapter: GithubLikeAdapter
    private lateinit var searchFragment: SearchFragment
    private lateinit var likeFragment: LikeFragment

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            searchFragment = SearchFragment.newInstance()
            searchFragment.setContext(view)
            searchFragment.setAdapter(searchAdapter)

            searchFragment
        } else {
            likeFragment = LikeFragment.newInstance()
            likeFragment.setContext(view)
            likeFragment.setAdapter(likeAdapter)

            likeFragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var titleText = ""
        when (position) {
            0 -> titleText = "SEARCH"
            1 -> titleText = "LIKE"
        }
        return titleText
    }

    override fun getCount(): Int {
        return 2
    }

    fun clearText() {
        searchFragment.clearText()
    }

    fun setView(input: GithubActivity) {
        view = input
    }

    fun getText(): String {
        return searchFragment.getString()
    }

    fun setAdapter(searchAdapter: GithubSearchAdapter, likeAdapter: GithubLikeAdapter) {
        this.searchAdapter = searchAdapter
        this.likeAdapter = likeAdapter
    }
}