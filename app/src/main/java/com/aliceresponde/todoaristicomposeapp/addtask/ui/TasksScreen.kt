package com.aliceresponde.todoaristicomposeapp.addtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TasksScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        var isDialogVisible by rememberSaveable { mutableStateOf(false) }

        AddTaskDialog(modifier = Modifier.align(Alignment.Center).padding(16.dp),
            isVisible = isDialogVisible,
            onDismiss = { isDialogVisible = false // TODO USE VM
                 },
            onAddTask = { /*TODO*/ }
        )

        AddFavButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            onClick = { isDialogVisible = true })
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
fun AddTaskDialog(modifier: Modifier, isVisible: Boolean, onDismiss: () -> Unit, onAddTask: (String) -> Unit) {
    var taskContent by rememberSaveable { mutableStateOf("") }
    if (isVisible) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(modifier = modifier.fillMaxWidth().background(Color.White, RoundedCornerShape(8.dp)).padding(16.dp)) {
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
                        onDismiss()
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
                ) { Text(text = "Add") }
            }
        }
    }
}
