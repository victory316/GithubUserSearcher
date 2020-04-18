package com.example.answer.github.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.answer.github.data.source.GithubRepository
import com.example.answer.github.data.source.local.GithubDatabase
import com.example.answer.github.viewmodel.GithubViewModelFactory

/**
 *
 *  InjectorUtils
 *
 *  - Database와 viewModel의 Injection을 돕는 class
 *
 */

object InjectorUtils {
    private fun getGithubRepository(context: Context): GithubRepository {
        return GithubRepository.getInstance(
            GithubDatabase.getInstance(context.applicationContext)!!.githubDao()
        )
    }

    fun proviedGithubViewModel(fragment: Fragment): GithubViewModelFactory{
        val repository = getGithubRepository(fragment.requireContext())
        return GithubViewModelFactory(repository, fragment)
    }
}