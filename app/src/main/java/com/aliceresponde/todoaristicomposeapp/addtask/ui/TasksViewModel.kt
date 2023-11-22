package com.aliceresponde.todoaristicomposeapp.addtask.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {
    private val _isDialogVisible = MutableLiveData<Boolean>()
    val isDialogVisible: LiveData<Boolean> = _isDialogVisible

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks


    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun closeDialog() {
        _isDialogVisible.value = false
    }

    fun addTask(task: String) {
        _tasks.add(TaskModel(description = task))
        _isDialogVisible.value = false
    }

    fun onTaskChecked(task: TaskModel) {
        val index = _tasks.indexOf(task)
        _tasks[index] = task.copy(isCompleted = !task.isCompleted)
    }

    fun onDeleteTask(task: TaskModel) {
        _tasks.remove(task) // if I made a copy of the data set directly in the adapter, this would not work

        //val index = _tasks.indexOf(task)
        //_tasks.removeAt(index)

        //val taskToDelete = _tasks.find { it.id == task.id }
        //_tasks.remove(taskToDelete)
    }
}