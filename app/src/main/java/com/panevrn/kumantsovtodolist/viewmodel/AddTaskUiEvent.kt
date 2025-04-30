package com.panevrn.kumantsovtodolist.viewmodel

sealed class AddTaskUiEvent {
    data class ShowToast(val message: String): AddTaskUiEvent()
    object CloseDialog: AddTaskUiEvent()
}