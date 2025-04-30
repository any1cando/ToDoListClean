package com.panevrn.kumantsovtodolist.di

import com.panevrn.domain.repository.TaskRepository
import com.panevrn.domain.usecase.addtask.AddTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddTaskUseCase(
        repository: TaskRepository
    ): AddTaskUseCase = AddTaskUseCase(repository)
}