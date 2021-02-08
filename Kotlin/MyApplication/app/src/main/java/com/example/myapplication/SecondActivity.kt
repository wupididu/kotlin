package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val vEdit = findViewById<EditText>(R.id.act2_edit)

        findViewById<Button>(R.id.act2_button).setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("result", vEdit.text.toString())
            setResult(0, i)
            finish()
        }

        val str = intent.getStringExtra("tag1")


        vEdit.setText(str)
    }


}