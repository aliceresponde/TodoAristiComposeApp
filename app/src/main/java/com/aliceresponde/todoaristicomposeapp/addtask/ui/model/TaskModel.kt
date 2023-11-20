package com.aliceresponde.todoaristicomposeapp.addtask.ui.model

import java.sql.Timestamp

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val description: String,
    var isDone: Boolean= false,
)
