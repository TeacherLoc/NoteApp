package com.example.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoListDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "task.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tasks"
        private const val COLUMN_ID ="id"
        private const val COLUMN_NAME ="name"
        private const val COLUMN_DECRISPTION = "decrisption"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_DECRISPTION TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun insertTask(note: Task) { // insert du lieu cho Sqlite Db
        val db = writableDatabase // viet vao db thong qua bien db
        val values = ContentValues().apply {

            put(COLUMN_NAME, note.name)
            put(COLUMN_DECRISPTION, note.decrisption)

        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}