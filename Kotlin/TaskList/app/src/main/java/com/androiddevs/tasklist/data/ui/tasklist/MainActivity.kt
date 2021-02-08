@file:Suppress("DEPRECATION")

package com.androiddevs.tasklist.data.ui.tasklist

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.other.TaskAdapter
import com.androiddevs.tasklist.data.ui.TaskApplication
import com.androiddevs.tasklist.data.ui.fragments.NoteFragment
import com.androiddevs.tasklist.data.ui.fragments.TaskListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.task_item.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskListFragment = TaskListFragment(this)
        val noteFragment = NoteFragment(this)

        makeCurrentFragment(taskListFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.taskList -> makeCurrentFragment(taskListFragment)
                R.id.Note -> makeCurrentFragment(noteFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}