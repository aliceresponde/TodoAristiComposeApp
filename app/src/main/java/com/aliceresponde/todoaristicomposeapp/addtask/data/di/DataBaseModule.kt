package com.aliceresponde.todoaristicomposeapp.addtask.data.di

import android.content.Context
import androidx.room.Room
import com.aliceresponde.todoaristicomposeapp.addtask.data.local.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): TodoDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = TodoDataBase::class.java,
            name = "TASK_DATABASE"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(dataBase: TodoDataBase) = dataBase.taskDao()
}