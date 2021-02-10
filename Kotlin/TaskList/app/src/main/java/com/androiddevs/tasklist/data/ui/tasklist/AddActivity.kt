package com.androiddevs.tasklist.data.ui.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.db.entities.NotesItem
import com.androiddevs.tasklist.data.db.entities.TaskItem
import com.androiddevs.tasklist.data.other.Constants
import kotlinx.android.synthetic.main.actitvity_edit_task.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Suppress("DEPRECATION")
class AddActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factoryT: TaskViewModelFactory by instance()
    private val factoryN: NoteViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitvity_edit_task)




        val viewModelN = ViewModelProviders.of(this, factoryN).get(NotesViewModel::class.java)
        val viewModelT = ViewModelProviders.of(this, factoryT).get(TaskViewModel::class.java)



        getIntents()

        fab_save.setOnClickListener{
            val desc = edit_description_text.text.toString()
            val title = edit_title_text.text.toString()
            if(desc != "" && title != ""){


                when(intent.getStringExtra(Constants.FROM_KEY)){
                    Constants.TASK_FRAGMENT -> {
                        if(intent.getIntExtra(Constants.ID, 0) != 0){

                            viewModelT.deleteById(intent.getIntExtra(Constants.ID, 0))
                        }

                        viewModelT.insert(TaskItem(title = title, description = desc, date = Date().time))
                    }
                    Constants.NOTE_FRAGMENT -> {
                        if(intent.getIntExtra(Constants.ID, 0) != 0){

                            viewModelN.deleteById(intent.getIntExtra(Constants.ID, 0))
                        }


                        viewModelN.insert(NotesItem(title = title, description = desc, date = Date().time))
                    }
                }


            }else{
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            finish()
        }
    }

    fun getIntents(){
        val i = intent

        if(i.getBooleanExtra(Constants.NEW, false) == false){

            if(i.getStringExtra(Constants.TITLE_KEY) != null){
                edit_title_text.setText(i.getStringExtra(Constants.TITLE_KEY))
                edit_description_text.setText(i.getStringExtra(Constants.DESCRIPTION_KEY))
            }

        }
    }
}