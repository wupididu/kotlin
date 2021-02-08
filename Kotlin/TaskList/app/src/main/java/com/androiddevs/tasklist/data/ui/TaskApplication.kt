package com.androiddevs.tasklist.data.ui

import android.app.Application
import com.androiddevs.tasklist.data.db.database.NotesDatabase
import com.androiddevs.tasklist.data.db.database.TaskDatabase
import com.androiddevs.tasklist.data.repositories.NotesRepository
import com.androiddevs.tasklist.data.repositories.TaskRepository
import com.androiddevs.tasklist.data.ui.tasklist.NoteViewModelFactory
import com.androiddevs.tasklist.data.ui.tasklist.TaskViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TaskApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@TaskApplication))
        bind() from singleton { TaskDatabase(instance()) }
        bind() from singleton { TaskRepository(instance()) }
        bind() from provider {
            TaskViewModelFactory(instance())
        }

        bind() from singleton { NotesDatabase(instance()) }
        bind() from singleton { NotesRepository(instance()) }
        bind() from provider {
            NoteViewModelFactory(instance())
        }
    }
}