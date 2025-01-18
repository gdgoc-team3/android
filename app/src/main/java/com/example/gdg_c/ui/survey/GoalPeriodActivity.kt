package com.example.gdg_c.ui.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gdg_c.R
import com.example.gdg_c.databinding.ActivityGoalPeriodBinding

class GoalPeriodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoalPeriodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goal_period)

        binding = ActivityGoalPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val birthDate = intent.getStringExtra("birth") ?: "생년월일 없음"
        val major = intent.getStringExtra("major") ?: "전공 없음"
        val desiredJob = intent.getStringExtra("desiredJob") ?: "직업 없음"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivNextBtn.setOnClickListener {

            val period1 = binding.myspinner2.selectedItem.toString().toIntOrNull() ?: 0
            val period2 = binding.myspinner.selectedItem.toString().toIntOrNull() ?: 0

            val targetEmploymentPeriod = period1 + period2

            val intent = Intent(this, NameActivity::class.java).apply {
                putExtra("targetEmploymentPeriod", targetEmploymentPeriod)
                putExtra("birth", birthDate)
                putExtra("major", major)
                putExtra("desiredJob", desiredJob)
            }
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        val array = arrayListOf(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12
        )

        val spinnerAdapter = ArrayAdapter(this, R.layout.item_spinner, array)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.myspinner.adapter = spinnerAdapter

        binding.myspinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position) as Int
//                    binding.selectedTextView.text = selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }


        val spinnerAdapter2 = ArrayAdapter(this, R.layout.item_spinner, array)
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.myspinner2.adapter = spinnerAdapter2

        binding.myspinner2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position) as Int
//                    binding.selectedTextView.text = selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }


    }
}