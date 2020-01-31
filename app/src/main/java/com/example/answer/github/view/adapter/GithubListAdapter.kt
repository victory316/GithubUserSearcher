package com.example.answer.github.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.answer.R
import com.example.answer.databinding.GithubItemBinding
import com.example.answer.github.viewmodel.GithubViewModel
import com.example.answer.github.data.GithubData
import com.example.answer.github.view.GithubActivity

/**
 *
 *  Reference
 *
 *  - https://github.com/android/sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/adapters/PlantAdapter.kt
 */

class GithubListAdapter : RecyclerView.Adapter< RecyclerView.ViewHolder>() {
    private var contacts: List<GithubData> = listOf()
    private lateinit var viewModel: GithubViewModel
    private lateinit var view : GithubActivity

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val view = GithubItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return PlantViewHolder(view, viewModel)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val user = contacts[position]
        (viewHolder as PlantViewHolder).bind(user)
    }

    class PlantViewHolder(
        private val binding: GithubItemBinding,
        private val viewModel: GithubViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GithubData) {
            binding.userName.text = item.name
            binding.score.text = item.score.toString()
            binding.favorite = item.favorite
            binding.favoriteIcon.setOnClickListener {
                if (item.favorite == 0) {
                    viewModel.updateList(1, item.name)
                    binding.favorite = 1
                } else {
                    viewModel.updateList(0, item.name)
                    binding.favorite = 0
                }
            }

            binding.imageUrl = item.avatar_url
        }
    }

    fun setView(view: GithubActivity) {
        this.view = view
    }

    fun setViewModel(model: GithubViewModel) {
        viewModel = model
    }

    fun setContacts(contacts: List<GithubData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}