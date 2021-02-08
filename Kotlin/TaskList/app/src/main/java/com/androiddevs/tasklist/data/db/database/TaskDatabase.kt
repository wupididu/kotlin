package com.androiddevs.tasklist.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddevs.tasklist.data.db.Dao.TaskDao
import com.androiddevs.tasklist.data.db.entities.TaskItem


@Database(
    entities = [TaskItem::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object{
        @Volatile
        private var instance: TaskDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context)
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TaskDatabase::class.java, "TaskDB.db").build()
    }
}