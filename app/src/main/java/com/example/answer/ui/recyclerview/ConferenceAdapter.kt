package com.example.answer.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.R
import com.example.answer.ui.ConferenceViewModel
import com.example.answer.ui.room.ConferenceData
import kotlinx.android.synthetic.main.conference_item.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat


class ConferenceAdapter: RecyclerView.Adapter<ConferenceAdapter.ViewHolder>() {
    private var contacts: List<ConferenceData> = listOf()
    private lateinit var viewModel : ConferenceViewModel

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
                    setupTimelineBars(indices.startTime, indices.endTime, timeBarArray,
                        conferenceData.name)
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

    fun setViewModel(viewModel: ConferenceViewModel) {
        this.viewModel = viewModel
    }

    @SuppressLint("SimpleDateFormat")
    private fun setupCurrentTimeIndicator(viewHolder: ViewHolder, startX: Float, width: Int) {
        val current = System.currentTimeMillis()

        val hourFormat: DateFormat = SimpleDateFormat("HH")
        val minuitFormat: DateFormat = SimpleDateFormat("mm")

        // 현재 시간을 가져옴
        val currentHour = hourFormat.format(current).toInt()
        val currentMin = minuitFormat.format(current).toInt()

        // 30분당 움직여야 할 width를 계산
        val widthForMove = width / (END_TIME - START_TIME + 1) * 2

        val minCount: Int

        val lineMovement: Float
        val textMovement: Float

        minCount = if (currentMin > HALF_HOUR) {
            1
        } else {
            0
        }

        when {
            currentHour <= START_TIME -> {
                lineMovement = startX
                textMovement = startX
            }
            currentHour >= END_TIME -> {
                lineMovement = startX + width
                textMovement = startX + width - widthForMove * 2
            }
            else -> {
                lineMovement = startX + widthForMove * ((currentHour - START_TIME - 1) * 2 + minCount - 1)
                textMovement = startX + widthForMove * ((currentHour - START_TIME) * 2 + minCount)
            }
        }

        viewHolder.itemView.line_indicator.x = lineMovement
        viewHolder.itemView.current_time_text.x = textMovement
    }

    // 예약된 시간에 한해 회색 bar의 visibility를 조정
    private fun setupTimelineBars(startTime: String, endTime: String, timeArray: List<View>,
                                  name : String) {
        val startTimeInt = startTime.toInt()
        val endTimeInt = endTime.toInt()

        val startHour = startTimeInt / 100
        val startMinute = startTimeInt % 100
        val endHour = endTimeInt / 100
        val endMinute = endTimeInt % 100

        // 9시면 0번 인덱스부터 시작해야하며, 18시가 마지막 19번째 index로 위치함.
        // 각 칸은 30분 단위로 나뉘어짐을 고려해야 함.
        val startIndex = (startHour - START_TIME) * 2 + (startMinute / HALF_HOUR)
        var endIndex = (endHour - START_TIME) * 2 + (endMinute / HALF_HOUR) - 1

        if (endIndex >= timeArray.size) endIndex = timeArray.size - 1

        for (i in startIndex..endIndex) {
            timeArray[i].visibility = View.VISIBLE
        }

        // 방 예약이 가득 찼을 경우 viewModel을 통해 RoomDB 업데이트
        if (isFull(timeArray)) {
            viewModel.updateFull(name,1)
        }
    }

    // 방 예약이 가득 찼는지 확인하는 function
    private fun isFull(timeArray: List<View>): Boolean{
        for (indices in timeArray) {
            if (indices.visibility == View.VISIBLE) {
                return false
            }
        }

        return true
    }

    // 회의실의 예약 가능시간 및 View 드로잉에 필요한 상수 설정
    companion object {
        const val START_TIME = 9
        const val END_TIME = 18
        const val HALF_HOUR = 30
    }
}