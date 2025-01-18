package com.example.gdg_c.ui.task.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.data.model.repsonse.schedule.TaskProgressResponse
import com.example.gdg_c.databinding.ItemCalendarDayBinding
import com.example.gdg_c.ui.my.adapter.CalendarAdapter

class TaskCalendarAdapter(
    private val onClick: (TaskProgressResponse.TaskProgressResponseItem) -> Unit
) : ListAdapter<TaskProgressResponse.TaskProgressResponseItem, TaskCalendarAdapter.ViewHolder>(
    diffUtil
) {

    inner class ViewHolder(private val binding: ItemCalendarDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TaskProgressResponse.TaskProgressResponseItem, position: Int) {

            binding.root.setOnClickListener { onClick(item) }

            binding.tvCalendarDay.text = item.day?.toString() ?: ""
            item.progress?.let {
                binding.sivCalendarDayColor.setBackgroundColor(
                    if (item.progress == 0) Color.WHITE
                    else if (item.progress <= 20) Color.parseColor("#9911FB44")
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskCalendarAdapter.ViewHolder {
        val binding =
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskCalendarAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }



    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<TaskProgressResponse.TaskProgressResponseItem>() {
                override fun areItemsTheSame(
                    oldItem: TaskProgressResponse.TaskProgressResponseItem,
                    newItem: TaskProgressResponse.TaskProgressResponseItem
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: TaskProgressResponse.TaskProgressResponseItem,
                    newItem: TaskProgressResponse.TaskProgressResponseItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}