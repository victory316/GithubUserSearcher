package com.example.answer.ui.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.ui.room.ConferenceData
import kotlinx.android.synthetic.main.conference_item.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat


class ConferenceAdapter: RecyclerView.Adapter<ConferenceAdapter.ViewHolder>() {
    private var contacts: List<ConferenceData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conference_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.timeline_view.post {
            setupCurrentTimeIndicator(viewHolder, viewHolder.itemView.timeline_view.x,
                viewHolder.itemView.timeline_view.width)
        }

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

        fun bind(conferenceData: ConferenceData) {

            val testReservation = conferenceData.reservations

            if (testReservation != null) {
                for(indices in testReservation) {
                    // 예약시간에 따라 timeline bar의 색상을 지정
                    setupTimelineBars(indices.startTime, indices.endTime, timeBarArray)
                }
            }

            nameTv.text = conferenceData.name
            numberTv.text = conferenceData.location
        }
    }

    fun setContacts(contacts: List<ConferenceData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    private fun setupCurrentTimeIndicator(viewHolder: ViewHolder, startX: Float, width: Int) {
        val current = System.currentTimeMillis()

        val hourFormat: DateFormat = SimpleDateFormat("HH")
        val minuitFormat: DateFormat = SimpleDateFormat("mm")
        val currentHour = hourFormat.format(current).toInt()
        val currentMin = minuitFormat.format(current).toInt()
        Log.d("widthTest" , "startX : $startX | width : $width")

        val widthForMove = width / 19

        var minCount = 0

        if (currentMin > 30) {
            minCount = 1
        }

        Log.d("timeTest", "minCount : $minCount")

        if (currentHour <= 9) {
            viewHolder.itemView.line_indicator.x = startX
            viewHolder.itemView.current_time_text.x = startX
        } else if (currentHour >= 18) {
            viewHolder.itemView.line_indicator.x = startX + width
        } else {
            viewHolder.itemView.line_indicator.x = startX + widthForMove * (currentHour - 9 + minCount)
            viewHolder.itemView.current_time_text.x = startX + widthForMove * (currentHour - 9 + minCount)
        }

        Log.d("timeTest" , "time : $currentHour | $currentMin")
    }

    private fun setupTimelineBars(startTime: String, endTime: String, timeArray: List<View>) {
        val startTimeInt = startTime.toInt()
        val endTimeInt = endTime.toInt()

        val startHour = startTimeInt / 100
        val startMinute = startTimeInt % 100
        val endHour = endTimeInt / 100
        val endMinute = endTimeInt % 100

        // 9시면 0번 인덱스부터 시작해야하며, 18시가 마지막 19번째 index로 위치함.
        // 각 칸은 30분 단위로 나뉘어짐을 고려해야 함.

        val startIndex = (startHour - 9) * 2 + (startMinute / 30)
        var endIndex = (endHour - 9) * 2 + (endMinute / 30) - 1

        if (endIndex >= timeArray.size) endIndex = timeArray.size - 1

        Log.d("recyclerViewTest", "parsed time : $startHour : $startMinute | $endHour : $endMinute")
        Log.d("recyclerViewTest", "startIndex : $startIndex endIndex : $endIndex")

        for (i in startIndex..endIndex) {
            timeArray[i].visibility = View.VISIBLE
        }
    }
}