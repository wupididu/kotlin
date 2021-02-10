package com.androiddevs.tasklist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "task_item")
class TaskItem(
    @ColumnInfo(name = "task_title")
    var title: String,
    @ColumnInfo(name = "task_description")
    var description: String,
    @ColumnInfo(name = "task_isDone")
    var isDone: Boolean = false,
    @ColumnInfo(name = "task_date")
    var date: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}