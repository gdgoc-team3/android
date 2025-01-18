package com.example.gdg_c.ui.my.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdg_c.R
import com.example.gdg_c.data.calendar.CalendarData
import com.example.gdg_c.data.calendar.CalendarDay
import com.example.gdg_c.databinding.ItemCalendarDayBinding

class CalendarAdapter(
    private val data: CalendarData
) : ListAdapter<CalendarData, CalendarAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CalendarDay) {

            item.day?.let {
                binding.tvCalendarDay.text = it.toString()
                binding.sivCalendarDayColor.setBackgroundColor(
                    if (item.progress == 0) Color.WHITE
                    else if (item.progress!! <= 20) Color.RED
                    else if (item.progress <= 40) Color.YELLOW
                    else if (item.progress <= 60) Color.GREEN
                    else if (item.progress <= 80) Color.BLUE
                    else Color.BLACK
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        val binding =
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolder, position: Int) {
        holder.bind(data.days[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalendarData>() {
            override fun areItemsTheSame(oldItem: CalendarData, newItem: CalendarData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CalendarData, newItem: CalendarData): Boolean {
                return oldItem == newItem
            }
        }
    }
}