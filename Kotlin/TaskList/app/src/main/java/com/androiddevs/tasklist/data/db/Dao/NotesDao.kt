package com.androiddevs.tasklist.data.db.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.tasklist.data.db.entities.NotesItem
import com.androiddevs.tasklist.data.db.entities.TaskItem

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NotesItem)

    @Delete
    suspend fun delete(note: NotesItem)

    @Query("SELECT * FROM notes_item ORDER BY id DESC")
    fun gatAllTaskList(): LiveData<List<NotesItem>>

    @Query("DElETE FROM notes_item WHERE id = :id")
    suspend fun deleteById(id: Int)
}