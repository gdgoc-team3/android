package com.example.gdg_c.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdg_c.R
import com.example.gdg_c.databinding.FragmentTaskBinding
import com.example.gdg_c.ui.task.adapter.TaskCalendarAdapter

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskCalendarAdapter: TaskCalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)

        initTaskCalendarAdapter()

        return binding.root
    }

    private fun initTaskCalendarAdapter() {
        taskCalendarAdapter = TaskCalendarAdapter { hasTask ->
            if (hasTask) {
                binding.clAddTaskContainer.visibility = View.GONE
                binding.clTaskListContainer.visibility = View.VISIBLE
            } else {
                binding.clAddTaskContainer.visibility = View.VISIBLE
                binding.clTaskListContainer.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}