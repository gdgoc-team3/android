package com.example.gdg_c.data.network

import com.example.gdg_c.data.remote.MyService
import com.example.gdg_c.data.remote.TaskService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.gdgoc-team3.site/"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val myService: MyService by lazy {
        retrofit.create(MyService::class.java)
    }
    val taskService: TaskService by lazy {
        retrofit.create(TaskService::class.java)
    }



}