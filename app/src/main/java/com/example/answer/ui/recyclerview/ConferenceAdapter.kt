package com.example.answer.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.ui.room.RoomData

class ConferenceAdapter(val contactItemClick: (RoomData) -> Unit, val contactItemLongClick: (RoomData) -> Unit)
    : RecyclerView.Adapter<ConferenceAdapter.ViewHolder>() {
    private var contacts: List<RoomData> = listOf()

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

        fun bind(roomData: RoomData) {
            nameTv.text = roomData.name
            numberTv.text = roomData.location

            itemView.setOnClickListener {
                contactItemClick(roomData)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(roomData)
                true
            }
        }
    }

    fun setContacts(contacts: List<RoomData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

}