package com.example.gdg_c.data.model.task


import com.google.gson.annotations.SerializedName

data class StartDate(
    val day: Int,
    val hour: Int,
    val minute: Int,
    val month: Int,
    val year: Int
)