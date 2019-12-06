package com.example.answer.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.ui.room.ConferenceRoomData

class ConferenceAdapter(val contactItemClick: (ConferenceRoomData) -> Unit, val contactItemLongClick: (ConferenceRoomData) -> Unit)
    : RecyclerView.Adapter<ConferenceAdapter.ViewHolder>() {
    private var contacts: List<ConferenceRoomData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conference_item, parent, false)
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

        fun bind(conferenceRoomData: ConferenceRoomData) {
            nameTv.text = conferenceRoomData.name
            numberTv.text = conferenceRoomData.location

            itemView.setOnClickListener {
                contactItemClick(conferenceRoomData)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(conferenceRoomData)
                true
            }
        }
    }

    fun setContacts(contacts: List<ConferenceRoomData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

}