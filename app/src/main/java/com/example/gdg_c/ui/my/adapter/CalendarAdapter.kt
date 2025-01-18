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
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.databinding.ItemCalendarDayBinding

class CalendarAdapter : ListAdapter<CalendarDay, CalendarAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CalendarDay, position: Int) {

            item.day?.let {
                binding.tvCalendarDay.text = it.toString()
                binding.sivCalendarDayColor.setBackgroundColor(
                    if (item.progress == 0) Color.WHITE
                    else if (item.progress!! <= 20) Color.parseColor("#9911FB44")
                    else if (item.progress <= 40) Color.parseColor("#6619D71F")
                    else if (item.progress <= 60) Color.parseColor("#B352BF6D")
                    else if (item.progress <= 80) Color.parseColor("#B353F607")
                    else Color.parseColor("#6CA683")
                )
            }
            if (position % 7 == 0) {
                binding.tvCalendarDay.setTextColor(Color.RED)
            } else if (position % 7 == 6) {
                binding.tvCalendarDay.setTextColor(Color.BLUE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        val binding =
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalendarDay>() {
            override fun areItemsTheSame(oldItem: CalendarDay, newItem: CalendarDay): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CalendarDay, newItem: CalendarDay): Boolean {
                return oldItem == newItem
            }
        }
    }
}