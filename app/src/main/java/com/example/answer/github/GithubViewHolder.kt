package com.example.answer.github

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class GithubViewHolder<out T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T = DataBindingUtil.bind(view)!!
}