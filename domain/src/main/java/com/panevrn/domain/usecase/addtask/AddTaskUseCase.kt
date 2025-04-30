package com.panevrn.domain.usecase.addtask

import com.panevrn.domain.model.TaskModel
import com.panevrn.domain.repository.TaskRepository
import java.time.LocalDateTime

class AddTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(request: AddTaskRequest): Long {
        val task = TaskModel(
            title = request.title,
            description = request.description,
            isCompleted = false,
            createdTime = LocalDateTime.now()
        )
        repository.addTask(task)
        return repository.addTask(task)
    }
}