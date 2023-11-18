package com.aliceresponde.todoaristicomposeapp.addtask.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {
    private val _isDialogVisible = MutableLiveData<Boolean>()
    val isDialogVisible: LiveData<Boolean> = _isDialogVisible

    private val _tasks = MutableLiveData<String>()
    val tasks: LiveData<String> = _tasks

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun closeDialog() {
        _isDialogVisible.value = false
    }

    fun addTask(task: String) {
        Log.d("TasksViewModel", "addTask: $task")
        //
        _isDialogVisible.value = false
    }
}