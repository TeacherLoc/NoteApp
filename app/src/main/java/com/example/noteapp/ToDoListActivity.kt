package com.example.noteapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToDoListBinding
    private lateinit var db: ToDoListDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ToDoListDatabaseHelper(this)

        binding.saveButton.setOnClickListener{ // taoj su kien tren nut them moi cua task

            val name = binding.titleEditText.text.toString()
            val decrisption = binding.contentEditText.text.toString()
            val task = Task(0, name, decrisption)
            db.insertTask(task)// theem moi du lieu cho data sql
            finish()
            Toast.makeText(this, "Task đã được thêm vào",Toast.LENGTH_SHORT).show()

        }
    }
}