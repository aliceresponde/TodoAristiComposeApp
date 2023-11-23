package com.aliceresponde.todoaristicomposeapp.addtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.todoaristicomposeapp.addtask.domain.AddTaskUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.DeleteTaskUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.GetTasksUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.UpdateTaskUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    var uiState: StateFlow<TaskUiState> = getTasksUseCase()
        .map(TaskUiState::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TaskUiState.Loading
        )

    private val _isDialogVisible = MutableLiveData<Boolean>()
    val isDialogVisible: LiveData<Boolean> = _isDialogVisible

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun closeDialog() {
        _isDialogVisible.value = false
    }

    fun addTask(task: String) {
        _isDialogVisible.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(description = task))
        }
    }

    fun onTaskChecked(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun onDeleteTask(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }
}