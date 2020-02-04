package com.example.answer.github.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.databinding.GithubItemBinding
import com.example.answer.github.data.GithubData
import com.example.answer.github.view.UserDiffCallback

class PersonAdapter(val context: Context) : PagedListAdapter<GithubData, PersonAdapter.PersonViewHolder>(
    UserDiffCallback()
) {

    override fun onBindViewHolder(holderPerson: PersonViewHolder, position: Int) {
        val person = getItem(position)

        if (person == null) {
            holderPerson.clear()
        } else {
            holderPerson.bind(person)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = GithubItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return PersonViewHolder(binding)
    }


    class PersonViewHolder (
        private val binding: GithubItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: GithubData) {
            binding.userName.text = person.name
            binding.favorite = person.favorite
            binding.imageUrl = person.avatar_url
            binding.score.text = person.score.toString()
        }

        fun clear() {
            binding.userName.text = null
            binding.favorite = null
            binding.imageUrl = null
            binding.score.text = null
        }

    }
}