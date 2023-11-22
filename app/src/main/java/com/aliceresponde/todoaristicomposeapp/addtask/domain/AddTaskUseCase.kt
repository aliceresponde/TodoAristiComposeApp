package com.aliceresponde.todoaristicomposeapp.addtask.domain

import com.aliceresponde.todoaristicomposeapp.addtask.data.TaskRepository
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: TaskModel): Unit = taskRepository.add(task)
}