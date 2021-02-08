package com.androiddevs.tasklist.data.other

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.db.entities.NotesItem
import com.androiddevs.tasklist.data.ui.tasklist.AddActivity
import com.androiddevs.tasklist.data.ui.tasklist.NotesViewModel
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(
    var noteList: List<NotesItem>,
    var viewModel: NotesViewModel,
    var context: Context
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    inner class NoteViewHolder(noteView: View): RecyclerView.ViewHolder(noteView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curTask = noteList[position]


        holder.itemView.tv_note_title.text = curTask.title
        holder.itemView.tv_note_date.text = curTask.date

        holder.itemView.setOnClickListener{
            val intent = Intent(context, AddActivity::class.java).apply {
                putExtra(Constants.FROM_KEY, Constants.NOTE_FRAGMENT)
                putExtra(Constants.NEW, false)
                putExtra(Constants.ID, curTask.id)
                putExtra(Constants.DESCRIPTION_KEY, curTask.description)
                putExtra(Constants.TITLE_KEY, curTask.title)
            }
            context.startActivity(intent)
        }

        holder.itemView.ib_note_delete.setOnClickListener{
            curTask.id?.let { it1 -> viewModel.deleteById(it1) }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}