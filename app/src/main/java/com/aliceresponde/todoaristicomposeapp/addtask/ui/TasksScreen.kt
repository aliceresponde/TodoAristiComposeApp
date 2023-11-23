package com.aliceresponde.todoaristicomposeapp.addtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.aliceresponde.todoaristicomposeapp.addtask.ui.model.TaskModel

@Composable
fun TasksScreen(viewModel: TasksViewModel) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<TaskUiState>(
        initialValue = TaskUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is TaskUiState.Loading -> { CircularProgressIndicator() }

        is TaskUiState.Success -> { TasksContent(viewModel, uiState as TaskUiState.Success) }

        is TaskUiState.Error -> { Text(text = "Error : ${(uiState as TaskUiState.Error).throwable.message}") }
    }
}

@Composable
fun TasksContent(viewModel: TasksViewModel, success: TaskUiState.Success) {
    val tasks = success.tasks
    Box(modifier = Modifier.fillMaxSize()) {
        val isDialogVisible by viewModel.isDialogVisible.observeAsState(false)

        AddTaskDialog(modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp),
            isVisible = isDialogVisible,
            onDismiss = { viewModel.closeDialog() },
            onAddTask = { viewModel.addTask(it) }
        )

        TaskList(viewModel = viewModel, tasks = tasks)

        AddFavButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { viewModel.showDialog() }
        )
    }
}

@Composable
fun TaskList(viewModel: TasksViewModel, tasks: List<TaskModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tasks, key = TaskModel::id) { task ->
            TaskItem(
                task = task,
                onTaskChecked = { viewModel.onTaskChecked(it) },
                onDeleteTask = { viewModel.onDeleteTask(it) }
            )
        }
    }
}

@Composable
fun TaskItem(
    task: TaskModel,
    onTaskChecked: (TaskModel) -> Unit,
    onDeleteTask: (TaskModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onDeleteTask(task) }
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.List, contentDescription = "Task icon")
            Text(
                text = task.description,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onTaskChecked(task) }
            )
        }
    }
}

@Composable
fun AddFavButton(modifier: Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add task",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    modifier: Modifier,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onAddTask: (String) -> Unit
) {
    var taskContent by rememberSaveable { mutableStateOf("") }
    if (isVisible) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add task",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = taskContent,
                    onValueChange = { taskContent = it },
                    label = { Text(text = "Task") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onAddTask(taskContent)
                        taskContent = ""
                    },
                    shape = MaterialTheme.shapes.medium,
                    enabled = taskContent.isNotBlank(),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) { Text(text = "Add") }
            }
        }
    }
}
