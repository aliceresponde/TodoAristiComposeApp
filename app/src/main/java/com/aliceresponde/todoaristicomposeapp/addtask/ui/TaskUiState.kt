package com.aliceresponde.todoaristicomposeapp.addtask.ui

import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel

sealed interface TaskUiState {
    data object Loading : TaskUiState
    data class Success(val tasks: List<TaskModel>) : TaskUiState
    data class Error(val throwable: Throwable) : TaskUiState

}
