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
        val botonInicio:Button =  findViewById(R.id.button7)
        val botonAzul:Button =  findViewById(R.id.button8)
        val texto:TextView = findViewById(R.id.textView3)
        val random: Int = (0..3).random()
        val colores: Array<String> = arrayOf("Verde", "Amarillo", "Azul", "Rojo")

        botonInicio.setOnClickListener() {
            inicioPartida()
        }

        botonAzul.setOnClickListener(){

        }


    }


    fun inicioPartida(){
        val botonAzul:Button =  findViewById(R.id.button8)
        val botonAmarillo:Button =  findViewById(R.id.button9)
        val botonRojo:Button =  findViewById(R.id.button10)
        val botonVerde:Button =  findViewById(R.id.button11)
        val random: Int = (0..3).random()
        val buleano:Boolean = true

        Toast.makeText(this,"Has iniciado partida",Toast.LENGTH_SHORT).show()

            if (random == 0) {
                botonAzul.setBackgroundColor(Color.CYAN)
            }
            if (random == 1) {
                botonAmarillo.setBackgroundColor(Color.YELLOW)
            }
            if (random == 2) {
                botonRojo.setBackgroundColor(Color.RED)
            }
            if (random == 3) {
                botonVerde.setBackgroundColor(Color.GREEN)
            }

    }


    fun botonAzul(){
        val botonAzul:Button =  findViewById(R.id.button8)


    }



    fun visualizaColor() {
        var encendido: Job? = null

        encendido = GlobalScope.launch(Dispatchers.Main) {
            var contador = 3
            while (contador > 0) {
                Log.d("Courutina", jobCuentaAtras.toString() + ":" + contandor.toString())
                delay(1000L) // non-blocking delay para 1 segundo
                contador--
            }

        }
        Log.d("Courutina", "Lanzada:" +
                " " + jobCuentaAtras.toString())

    }




}


