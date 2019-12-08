package com.example.answer.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.ui.room.ConferenceData

class ConferenceMiniAdapter : RecyclerView.Adapter<ConferenceMiniAdapter.ViewHolder>() {
    private var contacts: List<ConferenceData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conference_mini_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTv = itemView.findViewById<TextView>(R.id.room_name_text)

        fun bind(conferenceData: ConferenceData) {

            // 방이 가득 찼을경우 visiblilty를 조정
            if (conferenceData.is_full == 1) {
                itemView.visibility = View.INVISIBLE
            }

            nameTv.text = conferenceData.name
        }
    }

    fun setContacts(contacts: List<ConferenceData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

}