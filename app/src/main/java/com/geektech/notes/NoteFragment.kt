package com.geektech.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import com.geektech.notes.databinding.ActivityMainBinding
import com.geektech.notes.databinding.FragmentNoteBinding

class NoteFragment : Fragment(), IItemClick{

    private lateinit var binding: FragmentNoteBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NoteAdapter(this)

        binding.mainRecycler.adapter = adapter
        binding.sort.setOnClickListener {
            adapter.sort()
        }
    }

    override fun delete(pos: Int) {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle(getString(R.string.waring))
            alert.setMessage(getString(R.string.waring))
            alert.setPositiveButton("ok") { _, _ ->
                adapter.delete(pos)
            }
            alert.setNeutralButton("cancel", null)
            alert.show()
    }

    override fun edit(pos: Int) {
        val clickNote = adapter.getList()[pos]
        binding.editNote.setText(clickNote.title)
        binding.editNoteDesc.setText(clickNote.desc)
        binding.addNote.text = getString(R.string.edit)
        binding.addNote.setOnClickListener {
            val newNote = Note(binding.editNote.text.toString(), binding.editNoteDesc.text.toString())
            adapter.edit(pos, newNote)
            setAddListener()
        }
    }

    fun setAddListener() {
        binding.addNote.setOnClickListener {
            binding.addNote.setOnClickListener {
                if (binding.editNote.text.isBlank()) {
                    binding.editNote.error = "Error"
                } else {
                    val note = Note(
                        binding.editNote.text.toString(),
                        binding.editNoteDesc.text.toString())
                    adapter.addNote(note)
                    binding.editNote.text.clear()
                    binding.editNoteDesc.text.clear()
                    setAddListener()
                }
            }
        }
    }
}

