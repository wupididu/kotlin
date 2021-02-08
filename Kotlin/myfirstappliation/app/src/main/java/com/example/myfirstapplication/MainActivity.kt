package com.example.myfirstapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vText = findViewById<TextView>(R.id.act1_text)
        vText.setTextColor(0xFFFF0000.toInt())
        vText.setOnClickListener{
            Log.e("tag", "Нажата кнопка")
        }
        Log.v("tag","Был запущен OnCreate")
    }

    override fun onStart() { // Говорит о том что экран виден пользователю
        super.onStart()
    }

    override fun onResume() { // Говорит, что экран акивен, запускает всю анимацию
        super.onResume()
    }

    override fun onPause() { // Останавливает все, что было включено в onResume
        super.onPause()
    }

    override fun onStop() { // схоже с onPause
        super.onStop()
    }

    override fun onDestroy() { // убивает все процессы
        super.onDestroy()
    }
}