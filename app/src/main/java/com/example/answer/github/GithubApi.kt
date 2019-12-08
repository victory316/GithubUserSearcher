package com.example.answer.github

import com.example.answer.github.room.GithubData
import com.example.answer.github.room.GithubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET
    fun getRepos(@Path("owner") owner: String) : Single<ArrayList<GithubData>>
}