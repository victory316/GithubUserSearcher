package com.example.answer.github.room

import com.google.gson.annotations.SerializedName

data class GithubRepo(@SerializedName("name") val name: String)