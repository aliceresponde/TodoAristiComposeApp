package com.aliceresponde.todoaristicomposeapp.addtask.domain

import com.aliceresponde.todoaristicomposeapp.addtask.data.TaskRepository
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}