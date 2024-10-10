package com.example.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityUpdateTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db:ToDoListDatabaseHelper
    private var taskId : Int = 1.unaryMinus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ToDoListDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1)
        {
            finish()
            return
        }
        val task = db.getTaskByID(taskId)

        binding.updatetitleEditText.setText(task?.name)
        binding.updatecontentEditText.setText(task?.decrisption)

        binding.updatesaveButton.setOnClickListener{
            val newName = binding.updatetitleEditText.text.toString()
            val newDecrisption = binding.updatecontentEditText.text.toString()
            val updatedTask = Task(taskId, newName, newDecrisption)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Đã thay đổi", Toast.LENGTH_SHORT).show()
        }
    }
}