package com.aliceresponde.todoaristicomposeapp.addtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDataBase : RoomDatabase(){
    // DAO
    abstract fun taskDao(): TaskDao
}