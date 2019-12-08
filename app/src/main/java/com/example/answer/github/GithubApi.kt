package com.example.answer.github

import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepo
import com.example.answer.github.room.GithubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/users/{user}/repos")
    fun getRepos(@Path("user") users : String) : Single<ArrayList<GithubData>>
}