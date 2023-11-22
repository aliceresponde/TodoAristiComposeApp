package com.aliceresponde.todoaristicomposeapp.addtask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun selectAll(): Flow<List<TaskEntity>>

    @Insert
    suspend fun add(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun deleteCompleted(id: Long)
}
