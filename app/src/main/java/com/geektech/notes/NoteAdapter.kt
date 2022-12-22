package com.geektech.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var list: MutableList<String> = ArrayList()

    fun addNote(text: String){
        list.add(text)
        notifyItemInserted(list.size) // при ошибке пишем -1
    }

       inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var text = itemView.findViewById<TextView>(R.id.item_text)

            fun bind(message: String){
                text.text = message
                text.setOnClickListener{
                    list.removeAt(adapterPosition)
                    notifyItemRemoved(list.size)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}