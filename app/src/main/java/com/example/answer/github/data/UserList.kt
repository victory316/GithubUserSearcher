package com.example.answer.github.data

import com.example.answer.github.room.GithubRepo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserList {
    @SerializedName("items")
    @Expose
    lateinit var items: List<GithubRepo>

    fun getUserList(): List<GithubRepo> {
        return items
    }
}