package com.geektech.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IItemClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter(this)

        binding.mainRecycler.adapter = adapter
        // recyclerView.setAdapter(adapter)

        binding.addNote.setOnClickListener {
            if (binding.editNote.text.isBlank()) {
                 binding.editNote.error="Error"
            } else {
                val note = Note(binding.editNote.text.toString(), binding.editNoteDesc.text.toString())
                adapter.addNote(note)
                binding.editNote.text.clear()
                binding.editNoteDesc.text.clear()

            }
        }
    }

    override fun delete(pos: Int) {
        adapter.delete(pos)
    }

    override fun edit(pos: Int) {
        val clickNote = adapter.getList()[pos]
        binding.editNote.setText(clickNote.title)
        binding.editNoteDesc.setText(clickNote.desc)
        binding.addNote.setOnClickListener{
            val newNote = Note(binding.editNote.text.toString(), binding.editNoteDesc.text.toString())
            adapter.edit(pos, newNote)

        }
    }
}