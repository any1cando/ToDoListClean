package com.panevrn.data.repository

import com.panevrn.data.mapper.toDomain
import com.panevrn.data.mapper.toEntity
import com.panevrn.data.source.local.TaskDao
import com.panevrn.domain.model.TaskModel
import com.panevrn.domain.repository.TaskRepository
import javax.inject.Inject


class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun addTask(task: TaskModel): Long {
        return taskDao.insert(task.toEntity())
    }

//    override suspend fun getTasks(): List<TaskModel> {
//        return taskDao.getAll().map { it.toDomain() }
//    }
//
//    override suspend fun deleteTask(taskId: Long) {
//        taskDao.deleteById(taskId)
//    }
}