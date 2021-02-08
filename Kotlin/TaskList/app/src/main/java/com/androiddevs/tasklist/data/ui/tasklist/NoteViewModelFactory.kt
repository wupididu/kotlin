package com.androiddevs.tasklist.data.ui.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.tasklist.data.repositories.NotesRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(
    private val repository: NotesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(repository) as T
    }
}