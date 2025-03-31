package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val etName = findViewById<EditText>(R.id.etName)
        val etMSSV = findViewById<EditText>(R.id.etMSSV)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        studentAdapter = StudentAdapter(studentList) { position ->
            studentList.removeAt(position)
            studentAdapter.notifyItemRemoved(position)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val mssv = etMSSV.text.toString()
            if (name.isNotBlank() && mssv.isNotBlank()) {
                val newStudent = Student(name, mssv)
                studentList.add(0, newStudent)
                studentAdapter.notifyItemInserted(0)
                etName.text.clear()
                etMSSV.text.clear()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
