package com.aliceresponde.todoaristicomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aliceresponde.todoaristicomposeapp.addtask.ui.TasksScreen
import com.aliceresponde.todoaristicomposeapp.addtask.ui.TasksViewModel
import com.aliceresponde.todoaristicomposeapp.ui.theme.TodoAristiComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: TasksViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAristiComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TasksScreen(viewModel = viewModel)
                }
            }
        }
    }
}
