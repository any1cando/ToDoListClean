package com.panevrn.data.mapper

import com.panevrn.data.entity.TaskEntity
import com.panevrn.domain.model.TaskModel
import java.time.LocalDateTime
import java.time.ZoneOffset

fun TaskEntity.toDomain(): TaskModel = TaskModel(
    taskId = taskId,
    title = title,
    description = description,
    isCompleted = isCompleted,
    createdTime = LocalDateTime.ofEpochSecond(createdTime, 0, ZoneOffset.UTC)
)

fun TaskModel.toEntity(): TaskEntity = TaskEntity(
    taskId = taskId,
    title = title,
    description = description,
    isCompleted = isCompleted,
    createdTime = createdTime.toEpochSecond(ZoneOffset.UTC)
)