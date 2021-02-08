package com.androiddevs.tasklist.data.repositories

import com.androiddevs.tasklist.data.db.TaskDatabase
import com.androiddevs.tasklist.data.db.entities.TaskItem

class TaskRepository(
    private val db: TaskDatabase
) {
    suspend fun insert(task: TaskItem) = db.getTaskDao().insert(task)

    suspend fun delete(task: TaskItem) = db.getTaskDao().delete(task)

    fun getAllTaskList() = db.getTaskDao().gatAllTaskList()

    suspend fun deleteById(id: Int) = db.getTaskDao().deleteById(id)
}