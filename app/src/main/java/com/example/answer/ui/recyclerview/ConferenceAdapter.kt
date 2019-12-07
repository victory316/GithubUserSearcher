package com.example.answer.ui.recyclerview

import android.util.Log
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

        fun bind(conferenceRoomData: ConferenceRoomData) {

            Log.d("recyclerViewTest", "name : ${conferenceRoomData.name}")

            val testReservation = conferenceRoomData.reservations

            if (testReservation != null) {
                for(indices in testReservation) {
                    Log.d("recyclerViewTest", "starttime : ${indices.startTime}")
                    Log.d("recyclerViewTest", "endtime : ${indices.endTime}")

                    // 예약시간에 따라 timeline bar의 색상을 지정
                    setupTimelineBars(indices.startTime, indices.endTime, timeBarArray)
                }
            }

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

    fun setupTimelineBars(startTime: String, endTime: String, timeArray: List<View>){
        val startTimeInt = startTime.toInt()
        val endTimeInt = endTime.toInt()

        val startHour = startTimeInt / 100
        val startMinute = startTimeInt % 100
        val endHour = endTimeInt / 100
        val endMinute = endTimeInt % 100

        // 9시면 0번 인덱스부터 시작해야하며, 18시가 마지막 19번째 index로 위치함.
        // 각 칸은 30분 단위로 나뉘어짐을 고려해야 함.

        var startIndex = (startHour - 9) * 2 + (startMinute / 30)
        var endIndex = (endHour - 9) * 2 + (endMinute / 30)

        if (endIndex >= timeArray.size) endIndex = 17

        Log.d("recyclerViewTest", "parsed time : $startHour : $startMinute | $endHour : $endMinute")
        Log.d("recyclerViewTest", "startIndex : $startIndex endIndex : $endIndex")


        for (i in startIndex..endIndex) {
            timeArray[i].visibility = View.VISIBLE
        }

    }

}