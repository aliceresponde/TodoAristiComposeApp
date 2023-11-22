package com.aliceresponde.todoaristicomposeapp.addtask.data

import com.aliceresponde.todoaristicomposeapp.addtask.data.local.TaskDao
import com.aliceresponde.todoaristicomposeapp.addtask.data.local.TaskEntity
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    val tasks: Flow<List<TaskModel>> = taskDao.selectAll().map { list ->
        list.map(TaskEntity::toUiModel)
    }

    suspend fun add(task: TaskModel) = taskDao.add(task.toEntity())
}

private fun TaskEntity.toUiModel(): TaskModel {
    return TaskModel(
        id = id,
        description = description,
        isCompleted = isCompleted,
    )
}

private fun TaskModel.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        description = description,
        isCompleted = isCompleted,
    )
}
