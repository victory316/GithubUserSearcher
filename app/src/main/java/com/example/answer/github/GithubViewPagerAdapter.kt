package com.example.answer.github


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class GithubViewPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager?
) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment { // getItem is called to instantiate the fragment for the given page.
        return if (position == 0) {
            SearchFragment.newInstance()
        } else {
            LikeFragment.newInstance()
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

    companion object {
//        @StringRes
//        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }

}