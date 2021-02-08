package com.androiddevs.tasklist.data.repositories

import com.androiddevs.tasklist.data.db.database.NotesDatabase
import com.androiddevs.tasklist.data.db.entities.NotesItem
import com.androiddevs.tasklist.data.db.entities.TaskItem

class NotesRepository(
    private val db: NotesDatabase
) {
    suspend fun insert(note: NotesItem) = db.getNotesDao().insert(note)

    suspend fun delete(note: NotesItem) = db.getNotesDao().delete(note)

    fun getAllNoteList() = db.getNotesDao().gatAllTaskList()

    suspend fun deleteById(id: Int) = db.getNotesDao().deleteById(id)
}