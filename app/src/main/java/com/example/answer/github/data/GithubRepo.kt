package com.example.answer.github.data

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("score") val score: Double
)