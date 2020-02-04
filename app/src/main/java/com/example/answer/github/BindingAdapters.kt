package com.example.answer.github

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.answer.R
import com.example.answer.github.viewmodel.GithubViewModel

/**
 *  Reference
 *
 *  - https://androidwave.com/loading-images-using-data-binding/
 */

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

    // 이미지 적용
    @BindingAdapter("setImageWithGlide")
    @JvmStatic fun setImageWithGlide(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(ContextCompat.getDrawable(view.context, R.drawable.pending_profile_image))
            .crossFade()
            .into(view)

    }

    // 즐겨찾기 적용
    @BindingAdapter("setFavoriteIcon")
    @JvmStatic fun setFavoriteIcon(view: ImageView, favorite: Int) {
        if (favorite == 1) {
            view.setImageDrawable(
                ContextCompat.getDrawable(view.context,
                    R.drawable.like_icon))
        } else {

            view.setImageDrawable(
                ContextCompat.getDrawable(view.context,
                    R.drawable.unlike_icon))
        }
    }
}