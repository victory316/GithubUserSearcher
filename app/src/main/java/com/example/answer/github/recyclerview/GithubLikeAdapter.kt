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


class GithubLikeAdapter(val contactItemClick: (GithubData) -> Unit, val contactItemLongClick: (GithubData) -> Unit)
    : RecyclerView.Adapter<GithubLikeAdapter.ViewHolder>() {
    private var contacts: List<GithubData> = listOf()

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
        private val timeBarArray = listOf(
            itemView.findViewById<View>(R.id.bar_1),
            itemView.findViewById<View>(R.id.bar_2),
            itemView.findViewById<View>(R.id.bar_3),
            itemView.findViewById<View>(R.id.bar_4),
            itemView.findViewById<View>(R.id.bar_5),
            itemView.findViewById<View>(R.id.bar_6),
            itemView.findViewById<View>(R.id.bar_7),
            itemView.findViewById<View>(R.id.bar_8),
            itemView.findViewById<View>(R.id.bar_9),
            itemView.findViewById<View>(R.id.bar_10),
            itemView.findViewById<View>(R.id.bar_11),
            itemView.findViewById<View>(R.id.bar_12),
            itemView.findViewById<View>(R.id.bar_13),
            itemView.findViewById<View>(R.id.bar_14),
            itemView.findViewById<View>(R.id.bar_15),
            itemView.findViewById<View>(R.id.bar_16),
            itemView.findViewById<View>(R.id.bar_17),
            itemView.findViewById<View>(R.id.bar_18)
        )

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