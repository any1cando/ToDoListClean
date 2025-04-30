package com.panevrn.domain.usecase.addtask

data class AddTaskRequest(
    val title: String,
    val description: String?
)
