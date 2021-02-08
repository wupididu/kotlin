package com.androiddevs.tasklist.data.ui.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.tasklist.data.repositories.TaskRepository

@Suppress("UNCHECKED_CAST")
class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repository) as T
    }
}