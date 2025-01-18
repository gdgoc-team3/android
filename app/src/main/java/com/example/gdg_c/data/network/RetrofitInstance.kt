package com.example.gdg_c.data.network

import com.example.gdg_c.data.remote.MyService
import com.example.gdg_c.data.remote.SurveyService
import com.example.gdg_c.data.remote.TaskService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://api.gdgoc-team3.site/"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1200, TimeUnit.SECONDS)  // 연결 타임아웃 (기본값: 10초)
        .readTimeout(1200, TimeUnit.SECONDS)     // 읽기 타임아웃 (기본값: 10초)
        .writeTimeout(1800, TimeUnit.SECONDS)    // 쓰기 타임아웃 (기본값: 10초)
        .build()

    val myService: MyService by lazy {
        retrofit.create(MyService::class.java)
    }
    val taskService: TaskService by lazy {
        retrofit.create(TaskService::class.java)
    }

    val surveyService: SurveyService by lazy {
        retrofit.create(SurveyService::class.java)
    }


}