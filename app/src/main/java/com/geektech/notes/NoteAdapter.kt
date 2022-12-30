package com.geektech.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geektech.notes.databinding.ItemNoteBinding

class NoteAdapter(val listener: NoteFragment) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val list: MutableList<Note> = ArrayList()

    fun addNote(note: Note){
        list.add(note)
        notifyItemInserted(list.size) // при ошибке пишем -1
    }

    fun delete(pos: Int){
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun edit(pos: Int, note: Note){
        list.set(pos,note)
        notifyItemChanged(pos)

    }

    fun sort(){
        list.sortBy{ it.title}
        notifyDataSetChanged()
    }

    fun getList(): MutableList<Note>{
        return list
    }

       inner class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){

            fun bind(note: Note){
                binding.itemText.text = note.title
                binding.itemTextDesc.text = note.desc
                binding.iconDelete.setOnClickListener{
                    listener.delete(adapterPosition)

                }
                binding.iconEdit.setOnClickListener{
                    listener.edit(adapterPosition)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}