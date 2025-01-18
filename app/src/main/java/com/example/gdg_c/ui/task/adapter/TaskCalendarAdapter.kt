package com.example.gdg_c.ui.task.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.databinding.ItemCalendarDayBinding
import com.example.gdg_c.ui.my.adapter.CalendarAdapter

class TaskCalendarAdapter(
    private val onClick: (Boolean) -> Unit
) : ListAdapter<CalendarDay, TaskCalendarAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CalendarDay) {

            binding.root.setOnClickListener { onClick(item.hasTask) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskCalendarAdapter.ViewHolder {
        val binding =
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskCalendarAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
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