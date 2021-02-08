package com.androiddevs.tasklist.data.ui.tasklist

import androidx.lifecycle.ViewModel
import com.androiddevs.tasklist.data.db.entities.NotesItem
import com.androiddevs.tasklist.data.repositories.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
): ViewModel() {

    fun insert(note: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(note)
    }

    fun delete(note: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(note)
    }

    fun getAllNoteList() = repository.getAllNoteList()

    fun deleteById(id: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteById(id)
    }
}