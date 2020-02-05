package com.example.answer.github.data.source

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.answer.github.data.GithubData

class UserDiffCallback : DiffUtil.ItemCallback<GithubData>() {

    override fun areItemsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
        return oldItem == newItem
    }
}