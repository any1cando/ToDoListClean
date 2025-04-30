package com.panevrn.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val createdTime: Long
)