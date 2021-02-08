package com.example.note.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String, uri: String){
        val values = ContentValues().apply{
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMG_URI, uri)
        }
        db?.insert(MyDbNameClass.TABLE_NAME,null, values)
    }

    fun removeItemFromDb(id: String){
        val selection = BaseColumns._ID + "= $id"

        db?.delete(MyDbNameClass.TABLE_NAME,selection, null)
    }


    fun readDbData(): ArrayList<ListItem>{
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null,
            null,null,null,null)

        while(cursor?.moveToNext()!!){
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataTile = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            val dataContent = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CONTENT))
            val dataUri = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_IMG_URI))
            val item = ListItem(dataId, dataTile, dataContent, dataUri)
            dataList.add(0,item)
        }

        cursor.close()
        return dataList
    }

    fun closeDb(){
        myDbHelper.close()
    }
}