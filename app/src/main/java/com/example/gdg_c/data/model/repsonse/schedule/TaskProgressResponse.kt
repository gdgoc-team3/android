package com.example.gdg_c.data.model.repsonse.schedule


import com.google.gson.annotations.SerializedName

class TaskProgressResponse : ArrayList<TaskProgressResponse.TaskProgressResponseItem>(){
    data class TaskProgressResponseItem(
        val day: Int?,
        val progress: Int?
    )
}