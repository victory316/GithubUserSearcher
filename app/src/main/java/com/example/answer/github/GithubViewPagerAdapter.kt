package com.example.answer.github


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.answer.github.fragments.LikeFragment
import com.example.answer.github.fragments.SearchFragment
import com.example.answer.github.recyclerview.GithubLikeAdapter
import com.example.answer.github.recyclerview.GithubSearchAdapter


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class GithubViewPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager?
) :
    FragmentPagerAdapter(fm!!) {
    private lateinit var view: GithubActivity
    private lateinit var searchAdapter: GithubSearchAdapter
    private lateinit var likeAdapter: GithubLikeAdapter
    private lateinit var searchFragment: SearchFragment
    private lateinit var likeFragment: LikeFragment

    override fun getItem(position: Int): Fragment { // getItem is called to instantiate the fragment for the given page.
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
//        return mContext.resources
//            .getString(TAB_TITLES[position])
        var titleText = ""
        when (position) {
            0 -> titleText = "SEARCH"
            1 -> titleText = "LIKE"
        }
        return titleText
    }

    override fun getCount(): Int { // Show 2 total pages.
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

    companion object {
//        @StringRes
//        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }

}