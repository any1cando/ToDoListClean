package com.panevrn.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.panevrn.data.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: TaskEntity): Long

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<TaskEntity>

    @Query("DELETE FROM tasks WHERE taskId = :taskId")
    suspend fun deleteById(taskId: Long)
}