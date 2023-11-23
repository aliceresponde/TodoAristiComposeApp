package com.aliceresponde.todoaristicomposeapp.addtask.data.di

import com.aliceresponde.todoaristicomposeapp.addtask.data.TaskRepository
import com.aliceresponde.todoaristicomposeapp.addtask.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }
}