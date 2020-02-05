package com.example.answer.github.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.databinding.GithubItemBinding
import com.example.answer.github.data.GithubData
import com.example.answer.github.data.source.UserDiffCallback
import com.example.answer.github.viewmodel.GithubViewModel

class PagingAdapter(val context: Context) : PagedListAdapter<GithubData, PagingAdapter.PersonViewHolder>(
    UserDiffCallback()
) {

    private lateinit var viewModel: GithubViewModel

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

        return PersonViewHolder(
            binding,
            viewModel
        )
    }


    class PersonViewHolder (
        private val binding: GithubItemBinding,
        private val viewModel: GithubViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: GithubData) {
            binding.userName.text = person.name
            binding.favorite = person.favorite
            binding.imageUrl = person.avatar_url
            binding.score.text = person.score.toString()
            binding.favoriteIcon.setOnClickListener {
                if (person.favorite == 0) {
                    viewModel.updateList(1, person.name)
                    binding.favorite = 1
                } else {
                    viewModel.updateList(0, person.name)
                    binding.favorite = 0
                }
            }
        }

        fun clear() {
            binding.userName.text = null
            binding.favorite = null
            binding.imageUrl = null
            binding.score.text = null
        }
    }

    fun setViewModel(viewModel: GithubViewModel) {
        this.viewModel = viewModel
    }
}