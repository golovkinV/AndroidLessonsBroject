package com.study.androidstudy.screens.timeline.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.study.androidstudy.R
import com.study.androidstudy.screens.timeline.model.TimelineData
import kotlinx.android.synthetic.main.fragment_detail_timeline.view.*
import kotlinx.android.synthetic.main.item_timeline.view.*
import java.text.SimpleDateFormat
import java.util.*

class TimelineAdapter(
    private val onClick: (TimelineData) -> Unit
): RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    private var items = listOf<TimelineData>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: TimelineData) {
            itemView.apply {
                cardContainer.setOnClickListener {
                    onClick(item)
                }

                titleTextView.text = item.title
                descTextView.text = item.desc
                dateTextView.text = item.date.formatMonth()

                val today = System.currentTimeMillis()
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = today
                calendar.set(Calendar.HOUR, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

                if (item.isDone) {
                    val color = getColor(context, R.color.timeline_line_new)
                    titleTextView.setTextColor(color)
                    titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                    descTextView.setTextColor(color)
                }

                if (item.date < calendar.timeInMillis && !item.isDone) {
                    dateTextView.setTextColor(getColor(context, R.color.pass_time))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_timeline, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun set(items: List<TimelineData>) {
        this.items = listOf()
        this.items = items
        notifyDataSetChanged()
    }
}

@SuppressLint("SimpleDateFormat")
fun Long.formatMonth(): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}