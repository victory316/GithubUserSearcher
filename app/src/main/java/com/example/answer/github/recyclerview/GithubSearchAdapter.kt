package com.example.answer.github.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.github.room.GithubData
import com.example.answer.ui.room.ConferenceData
import kotlinx.android.synthetic.main.conference_item.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat


class GithubSearchAdapter(val contactItemClick: (GithubData) -> Unit, val contactItemLongClick: (GithubData) -> Unit)
    : RecyclerView.Adapter<GithubSearchAdapter.ViewHolder>() {
    private var contacts: List<GithubData> = listOf()
    private var barWidth = 0
    private var barStartX = 0f

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
        private val nameTv = itemView.findViewById<TextView>(R.id.room_name)
        private val numberTv = itemView.findViewById<TextView>(R.id.room_location)

        fun bind(githubData: GithubData) {

            Log.d("recyclerViewTest", "name : ${githubData.name}")


            nameTv.text = githubData.name
            numberTv.text = githubData.location

            itemView.setOnClickListener {
                contactItemClick(githubData)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(githubData)
                true
            }

//            setupCurrentTimeIndicator(itemView)
        }
    }

    fun setContacts(contacts: List<GithubData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}