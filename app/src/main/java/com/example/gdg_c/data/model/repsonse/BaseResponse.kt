package com.example.gdg_c.data.model.repsonse


import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val `data`: T,
    val message: String,
    val success: Boolean
)