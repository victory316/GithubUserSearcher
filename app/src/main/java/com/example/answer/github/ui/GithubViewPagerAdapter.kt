package com.example.answer.github.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import com.example.answer.github.ui.GithubListAdapter
import com.example.answer.github.ui.PagingAdapter
import com.example.answer.github.util.InjectorUtils
import com.example.answer.github.view.LikeFragment
import com.example.answer.github.view.SearchFragment
import com.example.answer.github.view.GithubActivity
import com.example.answer.github.viewmodel.GithubViewModel

class GithubViewPagerAdapter(
    fm: FragmentManager?
) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private lateinit var view: GithubActivity

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            SearchFragment.newInstance()
        } else {
            LikeFragment.newInstance()
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

    fun setView(input: GithubActivity) {
        view = input
    }
}