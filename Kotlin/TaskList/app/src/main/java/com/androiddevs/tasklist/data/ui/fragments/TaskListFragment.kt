package com.androiddevs.tasklist.data.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.other.Constants
import com.androiddevs.tasklist.data.other.TaskAdapter
import com.androiddevs.tasklist.data.ui.tasklist.AddActivity
import com.androiddevs.tasklist.data.ui.tasklist.TaskViewModel
import com.androiddevs.tasklist.data.ui.tasklist.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Constant
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

import org.kodein.di.generic.instance

@Suppress("DEPRECATION")
class TaskListFragment(
    private val contextM: Context
) : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: TaskViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val  rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        val viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)

        val adapter = TaskAdapter(listOf(), viewModel, contextM)

        val rw = rootView.findViewById(R.id.rw_tasks) as RecyclerView
        val fab_add_task = rootView.findViewById(R.id.fab_add_task) as FloatingActionButton


        rw.layoutManager = LinearLayoutManager(contextM)
        rw.adapter = adapter

        viewModel.getAllTaskList().observe(viewLifecycleOwner, Observer {
            adapter.taskList = it
            adapter.notifyDataSetChanged()
        })

        fab_add_task.setOnClickListener{
            val intent = Intent(contextM, AddActivity::class.java).apply {
                putExtra(Constants.FROM_KEY, Constants.TASK_FRAGMENT)
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