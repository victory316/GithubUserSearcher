package com.example.answer.github.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName
import java.security.acl.Owner

data class GithubRepository(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("language") val language: String?,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("master_branch") val masterBranch: String,
    @SerializedName("default_branch") val defaultBranch: String,
    @SerializedName("score") val score: Double
)