package com.dam2.simondice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Estado", "Estoy en onCreate")
        val boton:Button =  findViewById(R.id.button7)
        val texto:TextView = findViewById(R.id.textView3)

        boton.setOnClickListener() {
            inicioPartida()
        }


    }

    fun inicioPartida(){
        Toast.makeText(this,"Has iniciado partida",Toast.LENGTH_SHORT).show()
    }

}


