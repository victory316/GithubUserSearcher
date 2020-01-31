package com.example.answer.github

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.answer.github.viewmodel.GithubViewModel


object BindingAdapters {

    // 키보드 숨김 및 검색 수행
    @BindingAdapter("hideKeyboardAndDoSearch")
    @JvmStatic fun hideKeyboardAndDoSearch(view: ImageView, viewModel: GithubViewModel) {
        view.setOnClickListener {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            viewModel.doSearch()
        }
    }
}