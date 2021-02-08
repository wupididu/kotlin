package com.androiddevs.tasklist.data.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.other.Constants
import com.androiddevs.tasklist.data.other.NoteAdapter
import com.androiddevs.tasklist.data.other.TaskAdapter
import com.androiddevs.tasklist.data.ui.tasklist.AddActivity
import com.androiddevs.tasklist.data.ui.tasklist.NoteViewModelFactory
import com.androiddevs.tasklist.data.ui.tasklist.NotesViewModel
import com.androiddevs.tasklist.data.ui.tasklist.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

@Suppress("DEPRECATION")
class NoteFragment(
    private val contextM: Context
) : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: NoteViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_note, container, false)

        val viewModel = ViewModelProviders.of(this, factory).get(NotesViewModel::class.java)

        val adapter = NoteAdapter(listOf(), viewModel, contextM)

        val rw_notes = rootView.findViewById(R.id.rw_notes) as RecyclerView

        val fab_add_note = rootView.findViewById(R.id.fab_add_note) as FloatingActionButton

        rw_notes.layoutManager = LinearLayoutManager(contextM)
        rw_notes.adapter = adapter

        viewModel.getAllNoteList().observe(viewLifecycleOwner, Observer {
            adapter.noteList = it
            adapter.notifyDataSetChanged()
        })

        fab_add_note.setOnClickListener{
            val intent = Intent(contextM, AddActivity::class.java).apply {
                putExtra(Constants.FROM_KEY, Constants.NOTE_FRAGMENT)
                putExtra(Constants.NEW, true)
            }
            contextM.startActivity(intent)
        }

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}