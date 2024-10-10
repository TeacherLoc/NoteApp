package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var db: ToDoListDatabaseHelper
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ToDoListDatabaseHelper(this)
        taskAdapter = TaskAdapter(db.getAllTask(), this)

        binding.taskRecycleView.layoutManager = LinearLayoutManager(this)
        binding.taskRecycleView.adapter = taskAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, ToDoListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getAllTask())
    }
}