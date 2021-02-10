package com.androiddevs.tasklist.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddevs.tasklist.data.db.Dao.NotesDao
import com.androiddevs.tasklist.data.db.entities.NotesItem


@Database(
    entities = [NotesItem::class],
    version = 2
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object{
        @Volatile
        private var instance: NotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context)
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NotesDatabase::class.java, "NotesDB.db").build()
    }
}