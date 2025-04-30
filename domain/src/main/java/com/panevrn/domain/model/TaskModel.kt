package com.panevrn.domain.model

import java.time.LocalDateTime

data class TaskModel(
    val taskId: Long = 0L,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val createdTime: LocalDateTime
)
