package com.example.gdg_c.ui.task.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.data.model.repsonse.schedule.TaskListResponse
import com.example.gdg_c.data.model.repsonse.schedule.TaskResponse
import com.example.gdg_c.databinding.ItemCalendarDayBinding
import com.example.gdg_c.databinding.ItemCheckListBinding

class TaskListAdapter : ListAdapter<TaskListResponse.TaskListResponseItem.Task, TaskListAdapter.TaskViewHolder>(diffUtil) {

    inner class TaskViewHolder(private val binding: ItemCheckListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TaskListResponse.TaskListResponseItem.Task) {
            binding.tvTaskItem.text = item.title
            binding.tvTaskStartDate.text = item.startDate.toString()
            binding.tvTaskEndDate.text = item.endDate.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            ItemCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TaskListResponse.TaskListResponseItem.Task>() {
            override fun areItemsTheSame(
                oldItem: TaskListResponse.TaskListResponseItem.Task,
                newItem: TaskListResponse.TaskListResponseItem.Task
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TaskListResponse.TaskListResponseItem.Task,
                newItem: TaskListResponse.TaskListResponseItem.Task
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}