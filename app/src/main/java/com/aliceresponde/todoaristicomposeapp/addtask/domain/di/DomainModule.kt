/*package com.aliceresponde.todoaristicomposeapp.addtask.domain.di

import com.aliceresponde.todoaristicomposeapp.addtask.data.TaskRepository
import com.aliceresponde.todoaristicomposeapp.addtask.domain.AddTaskUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.DeleteTaskUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.GetTasksUseCase
import com.aliceresponde.todoaristicomposeapp.addtask.domain.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    @Singleton
    fun provideAddTaskUseCase(repository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetTasksUseCase(repository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesUpdateTaskUseCase(repository: TaskRepository): UpdateTaskUseCase {
        return UpdateTaskUseCase(repository)
    }
}
*/