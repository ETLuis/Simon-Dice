package com.dam2.simondice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Estado", "Estoy en onCreate")

        val random: Int = (0..3).random()
        val colores: Array<String> = arrayOf("Green", "Yellow", "Blue", "Red")
        val todosColores: ArrayList<String> = arrayListOf(colors(random))
        val start = findViewById<Button>(R.id.button7)
        val Array: Array<Class<Green>> = arrayOf(Green::class.java)

        for(i: Int in 0..3){
            random = (0..3).random()
            todosColores.add(colores(colores))
        }

        start.setOnClickListener{
            val intent = Intent(this@MainActivity, Green::class.java)
            intent.putStringArrayListExtra("colors, allColors")
            intent.putExtra("count", 0)
            intent.putExtra("score", 0)
            startActivity(intent)

        }

    }





}