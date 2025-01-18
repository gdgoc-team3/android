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
import com.example.gdg_c.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey)

        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivNextBtn.setOnClickListener {
            val intent = Intent(this, GoalPeriodActivity::class.java)
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        val monthArray = arrayListOf(
            "Option",
            "기획/전략",
            "마케팅/광고/MD",
            "디자인",
            "엔지니어링",
            "식/음료",
            "교육",
            "의료/바이오",
            "공공/복지",
            "법무/사무/총무",
            "회계/세무",
            "개발/데이터",
            "물류/무역",
            "금융/보험",
            "제조/생산",
            "건축",
            "미디어/문화/스포츠"
        )

        val spinnerAdapter = ArrayAdapter(this, R.layout.item_spinner, monthArray)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter

        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position) as String
                    if (selectedItem == "Option") {

                    }
//                    binding.selectedTextView.text = selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }
    }
}