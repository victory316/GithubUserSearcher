package com.example.answer.github

import com.example.answer.github.room.GithubData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{owner}/repos")
    fun listRepos(@Path("owner") owner: String?): Call<List<GithubData?>?>?
}