package com.example.note

import android.content.Intent
import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.db.MyAdapter
import com.example.note.db.MyDbManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    fun onClickNew(view: View){

        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)

    }

    override fun onResume() {

        super.onResume()
        myDbManager.openDb()
        fillAdapter()

    }

    override fun onDestroy() {

        super.onDestroy()
        myDbManager.closeDb()

    }

    fun init(){

        rcView.layoutManager = LinearLayoutManager(this)

        val swapAction = getSwapAction()

        swapAction.attachToRecyclerView(rcView)

        rcView.adapter = myAdapter

    }

    fun fillAdapter(){

        val list = myDbManager.readDbData()
        myAdapter.updateAdapter(list)

        if (list.size > 0) {

            tvNoElements.visibility = View.GONE

        }else{

            tvNoElements.visibility = View.VISIBLE

        }

    }

    private fun getSwapAction(): ItemTouchHelper{

        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })

    }

}

