package com.panevrn.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.panevrn.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}