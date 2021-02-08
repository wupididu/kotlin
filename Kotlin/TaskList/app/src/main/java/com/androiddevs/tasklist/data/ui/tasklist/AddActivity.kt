package com.androiddevs.tasklist.data.ui.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.db.entities.TaskItem
import com.androiddevs.tasklist.data.other.Constants
import kotlinx.android.synthetic.main.actitvity_edit_task.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

@Suppress("DEPRECATION")
class AddActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: TaskViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitvity_edit_task)

        val viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)

        getIntents()

        fab_save.setOnClickListener{
            val desc = edit_description_text.text.toString()
            val title = edit_title_text.text.toString()
            if(desc != "" && title != ""){

                if(intent.getIntExtra(Constants.ID, 0) != 0){
                    viewModel.deleteById(intent.getIntExtra(Constants.ID, 0))
                }

                viewModel.insert(TaskItem(title = title, description = desc))
            }else{
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            finish()
        }
    }

    fun getIntents(){
        val i = intent

        if(i != null){

            if(i.getStringExtra(Constants.TITLE_KEY) != null){
                edit_title_text.setText(i.getStringExtra(Constants.TITLE_KEY))
                edit_description_text.setText(i.getStringExtra(Constants.DESCRIPTION_KEY))
            }

        }
    }
}