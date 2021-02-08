package com.androiddevs.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.todolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var rcView: RecyclerView
    lateinit var adapter: ActivityAdapter
    lateinit var list: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arrayOf("sdf","sdfsdf","sdsefefds")
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        rcView = mBinding.rcView
        adapter = ActivityAdapter(list)


        rcView.adapter = adapter
        setContentView(mBinding.root)

    }
}