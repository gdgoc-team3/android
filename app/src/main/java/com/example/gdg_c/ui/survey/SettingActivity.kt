package com.example.gdg_c.ui.survey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gdg_c.MainActivity
import com.example.gdg_c.R
import com.example.gdg_c.data.repository.UserRepository
import com.example.gdg_c.databinding.ActivitySettingBinding
import kotlinx.coroutines.launch
import retrofit2.http.Body
import java.util.UUID

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    private var repository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setting)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val birthDate = binding.birth.text.toString()
        val major = binding.major.text.toString()
        val desiredJob = binding.desiredJob.text.toString()
        val userIdentity = generateRandomUserIdentity()
        val nickname = intent.getStringExtra("nickname") ?: "닉네임 없음"
        val targetEmploymentPeriod = intent.getIntExtra("targetEmploymentPeriod", 0)

        binding.nickname.text = nickname


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            postUser(birthDate, nickname, userIdentity, major, desiredJob, targetEmploymentPeriod)

            ivStartBtn.setOnClickListener {
                val intent = Intent(this@SettingActivity, MainActivity::class.java)
                startActivity(intent)
            }
            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun postUser(
        birthDate: String,
        nickname: String,
        userIdentity: String,
        major: String,
        desiredJob: String,
        targetEmploymentPeriod: Int
    ) {
        lifecycleScope.launch {
            kotlin.runCatching {
                Log.d("parameter", "birthDate: $birthDate, nickname: $nickname, userIdentity: $userIdentity, major: $major, desiredJob: $desiredJob, targetEmploymentPeriod: $targetEmploymentPeriod")

                repository.postUser(
                    "2000-11-22",
                    nickname,
                    userIdentity,
                    major,
                    desiredJob,
                    targetEmploymentPeriod
                )
            }.onSuccess {
                Log.d("success", "postUser Success")
            }.onFailure {
                Log.e("fail", "postUser Failed: ${it.message}")
            }
        }
    }

    private fun generateRandomUserIdentity(): String {
        return UUID.randomUUID().toString().replace("-", "").take(16)
    }
}