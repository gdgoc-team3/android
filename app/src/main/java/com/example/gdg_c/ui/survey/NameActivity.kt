package com.example.gdg_c.ui.survey

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gdg_c.R
import com.example.gdg_c.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_name)

        val targetEmploymentPeriod = intent.getIntExtra("targetEmploymentPeriod", 0)
        val birthDate = intent.getStringExtra("birth") ?: "생년월일 없음"
        val major = intent.getStringExtra("major") ?: "전공 없음"
        val desiredJob = intent.getStringExtra("desiredJob") ?: "직업 없음"

        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivNextBtn.setOnClickListener {

            val nickname = binding.etNickname.text.toString()

            val intent = Intent(this, SettingActivity::class.java).apply {
                putExtra("nickname", nickname)
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
    }
}