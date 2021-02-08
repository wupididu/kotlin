package com.androiddevs.tasklist.data.db.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.tasklist.data.db.entities.TaskItem

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    @Query("SELECT * FROM task_item ORDER BY task_isDone ASC, id DESC")
    fun gatAllTaskList(): LiveData<List<TaskItem>>

    @Query("DElETE FROM task_item WHERE id = :id")
    suspend fun deleteById(id: Int)
}