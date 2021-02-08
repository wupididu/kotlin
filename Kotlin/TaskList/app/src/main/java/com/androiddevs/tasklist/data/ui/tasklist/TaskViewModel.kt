package com.androiddevs.tasklist.data.ui.tasklist


import androidx.lifecycle.ViewModel
import com.androiddevs.tasklist.data.db.entities.TaskItem
import com.androiddevs.tasklist.data.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel(){

    fun insert(task: TaskItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(task)
    }

    fun delete(task: TaskItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(task)
    }

    fun getAllTaskList() = repository.getAllTaskList()

    fun deleteById(id: Int) = CoroutineScope(Dispatchers.Main).launch{
        repository.deleteById(id)
    }

}