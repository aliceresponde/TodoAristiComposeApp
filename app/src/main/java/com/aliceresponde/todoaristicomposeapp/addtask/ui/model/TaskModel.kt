package com.aliceresponde.todoaristicomposeapp.addtask.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val description: String,
    var isCompleted: Boolean= false,
)
