package com.panevrn.kumantsovtodolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panevrn.domain.usecase.addtask.AddTaskRequest
import com.panevrn.domain.usecase.addtask.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
): ViewModel() {

    private val _addTaskState = MutableStateFlow<AddTaskUiState>(AddTaskUiState.Idle)
    val addTaskState: StateFlow<AddTaskUiState> = _addTaskState.asStateFlow()
    private val _eventFlow = MutableSharedFlow<AddTaskUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onAddTask(request: AddTaskRequest) {
        viewModelScope.launch {
            _addTaskState.value = AddTaskUiState.Loading

            try {
                val newTaskId = addTaskUseCase(request)
                _addTaskState.value = AddTaskUiState.Success
                _eventFlow.emit(AddTaskUiEvent.ShowToast("Создана задача с ID: $newTaskId"))
            } catch (e: Exception) {
                _addTaskState.value = AddTaskUiState.Error("StateFlow - Ошибка при добавлении задачи!")
                _eventFlow.emit(AddTaskUiEvent.CloseDialog)
            }
        }
    }

}