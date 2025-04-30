package com.panevrn.domain.repository

import com.panevrn.domain.model.TaskModel

interface TaskRepository {
    suspend fun addTask(task: TaskModel): Long
}