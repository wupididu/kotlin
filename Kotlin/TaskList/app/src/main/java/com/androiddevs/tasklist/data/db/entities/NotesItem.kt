package com.androiddevs.tasklist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "notes_item")
class NotesItem(
    @ColumnInfo(name = "notes_title")
    var title: String,
    @ColumnInfo(name = "notes_description")
    var description: String,
    @ColumnInfo(name = "notes_date")
    var date: Long
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}