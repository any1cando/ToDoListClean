package com.panevrn.kumantsovtodolist.viewmodel

sealed class AddTaskUiState {
    object Idle : AddTaskUiState()
    object Loading : AddTaskUiState()
    object Success : AddTaskUiState()
    data class Error(val message: String) : AddTaskUiState()
}