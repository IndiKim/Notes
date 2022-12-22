package com.geektech.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recycler)
        editText = findViewById(R.id.edit_note)
        button = findViewById(R.id.add_note)
        adapter = NoteAdapter()

        recyclerView.adapter = adapter
        // recyclerView.setAdapter(adapter)

        button.setOnClickListener {
            if (editText.text.isBlank()) {
                editText.error = "Error"
            } else {
                adapter.addNote(editText.text.toString())
                editText.setText("")
            }
        }
    }
}