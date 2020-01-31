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


class GithubSearchAdapter : RecyclerView.Adapter<GithubSearchAdapter.ViewHolder>() {
    private var contacts: List<GithubData> = listOf()
    private lateinit var viewModel: GithubViewModel
    private lateinit var view : GithubActivity

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.github_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTv = itemView.findViewById<TextView>(R.id.user_name)
        private val scoreTv = itemView.findViewById<TextView>(R.id.score)
        private val profileImage = itemView.findViewById<ImageView>(R.id.avatar_image)
        private val favorite = itemView.findViewById<ImageView>(R.id.favorite_icon)

        fun bind(githubData: GithubData) {
            nameTv.text = githubData.name
            scoreTv.text = githubData.score.toString()

            if (githubData.favorite != 0) {
                checkFavorite()
            } else {
                uncheckFavorite()
            }

            favorite.setOnClickListener {
                if (githubData.favorite == 0) {
                    checkFavorite()
                    viewModel.updateList(1, githubData.name)
                } else {
                    uncheckFavorite()
                    viewModel.updateList(0, githubData.name)
                }
            }

            Glide.with(view.applicationContext).load(githubData.avatar_url).into(profileImage)
        }

        private fun checkFavorite() {
            favorite.setImageDrawable(
                ContextCompat.getDrawable(view.applicationContext,
                    R.drawable.like_icon))
        }

        private fun uncheckFavorite() {
            favorite.setImageDrawable(
                ContextCompat.getDrawable(view.applicationContext,
                    R.drawable.unlike_icon))
        }
    }

//    class PlantViewHolder(
//        private val binding: GithubItemBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: GithubData) {
//            binding.userName.text = item.name
//            binding.score.text = item.score
//        }
//    }

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