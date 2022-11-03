package com.dam2.simondice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonInicio: Button = findViewById(R.id.button7)
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)
        val texto: TextView = findViewById(R.id.textView3)
        val random: Int = (0..3).random()
        val colores: Array<String> = arrayOf("Verde", "Amarillo", "Azul", "Rojo")

        botonInicio.setOnClickListener() {
            inicioPartida()
            botonInicio.visibility = View.INVISIBLE
            secuenciaCourutina()


        }


    }


    fun inicioPartida() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)
        val random: Int = (0..3).random()
        val buleano: Boolean = true


        botonAzul.visibility = View.INVISIBLE
        botonAmarillo.visibility = View.INVISIBLE
        botonRojo.visibility = View.INVISIBLE
        botonVerde.visibility = View.INVISIBLE

        Toast.makeText(this, "Has iniciado partida", Toast.LENGTH_SHORT).show()
    }

    fun secuenciaCourutina() {
        val job = GlobalScope.launch(Dispatchers.Main) {
        visualizarSecuencia()
    }
    }

    suspend fun visualizarSecuencia() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)
        var colores = arrayListOf<String>()

       for(z in 1..8){

           println(colores)

           var random: Int = (0..4).random()
           var num1: Int = 0
           var num2: Int = 0
           var num3: Int = 0
           var num4: Int = 0

           if (random==1){
               if(num1==0) {
                   botonAzul.visibility = View.VISIBLE
                   delay(1000L)
                   botonAzul.visibility = View.INVISIBLE
                   num1++
                   colores.add("Azul")
               }
           }

           if (random==2){
               if(num2==0) {
                   botonAmarillo.visibility = View.VISIBLE
                   delay(1000L)
                   botonAmarillo.visibility = View.INVISIBLE
                   num2++
                   colores.add("Amarillo")
               }
           }

           if (random==3){
               if(num3==0) {
                   botonRojo.visibility = View.VISIBLE
                   delay(1000L)
                   botonRojo.visibility = View.INVISIBLE
                    num3++
                   colores.add("Rojo")
               }
           }
           if (random==4){
               if(num4==0) {
                   botonVerde.visibility = View.VISIBLE
                   delay(1000L)
                   botonVerde.visibility = View.INVISIBLE
                   num4++
                   colores.add("Verde")
               }
           }

        }

        botonAzul.visibility = View.VISIBLE
        botonAmarillo.visibility = View.VISIBLE
        botonRojo.visibility = View.VISIBLE
        botonVerde.visibility = View.VISIBLE

    }



}




