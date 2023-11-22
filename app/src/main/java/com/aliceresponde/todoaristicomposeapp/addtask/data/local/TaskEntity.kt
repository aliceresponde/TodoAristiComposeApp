package com.aliceresponde.todoaristicomposeapp.addtask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val isCompleted: Boolean
)
