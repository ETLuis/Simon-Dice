package com.dam2.simondice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //Contador para comprobar la secuencia
    var contador: Int = 0
    var contadorJugador: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonInicio: Button = findViewById(R.id.button7)

        botonInicio.setOnClickListener() {
            Toast.makeText(this, "Has iniciado partida", Toast.LENGTH_SHORT).show()
            inicioPartida()
            botonInicio.visibility = View.INVISIBLE
        }
    }


    fun inicioPartida() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)

        contador = 0
        contadorJugador = 0

        botonAzul.visibility = View.INVISIBLE
        botonAmarillo.visibility = View.INVISIBLE
        botonRojo.visibility = View.INVISIBLE
        botonVerde.visibility = View.INVISIBLE

        secuenciaCourutina()
    }

    fun secuenciaCourutina() {
        val job = GlobalScope.launch(Dispatchers.Main) {
            visualizarSecuencia()
        }
    }

    //Este es el array de la Secuencia Aleatoria que se usa en el visualizarSecuencia y en miSec para comparar los Arrays
    var colores = arrayListOf<String>()


    suspend fun visualizarSecuencia() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)

        colores.clear()

        for (z in 0..4) {
            var random: Int = (1..4).random()


            if (random == 1) {
                    botonAzul.visibility = View.VISIBLE
                    delay(1000L)
                    botonAzul.visibility = View.INVISIBLE
                    delay(1000L)
                    colores.add("Azul")
                    contador++
            }

            if (random == 2) {
                    botonAmarillo.visibility = View.VISIBLE
                    delay(1000L)
                    botonAmarillo.visibility = View.INVISIBLE
                    delay(1000L)
                    colores.add("Amarillo")
                    contador++
            }

            if (random == 3) {
                    botonRojo.visibility = View.VISIBLE
                    delay(1000L)
                    botonRojo.visibility = View.INVISIBLE
                    delay(1000L)
                    colores.add("Rojo")
                    contador++
            }
            if (random == 4) {
                    botonVerde.visibility = View.VISIBLE
                    delay(1000L)
                    botonVerde.visibility = View.INVISIBLE
                    delay(1000L)
                    colores.add("Verde")
                    contador++
            }

            println(colores)
        }

        botonAzul.visibility = View.VISIBLE
        botonAmarillo.visibility = View.VISIBLE
        botonRojo.visibility = View.VISIBLE
        botonVerde.visibility = View.VISIBLE

        miSec()
    }

    //Este es el array del jugador, lo uso después de la secuencia random
    var miSecuencia = arrayListOf<String>()

    fun miSec() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)

        println(colores.size)
        var numeroColores: Int = colores.size

        miSecuencia.clear()

        botonAzul.setOnClickListener() {
            miSecuencia.add("Azul")
            println("Secuencia jugador:" + miSecuencia)
            contadorJugador++
            comprobarSec()
        }
        botonAmarillo.setOnClickListener() {
            miSecuencia.add("Amarillo")
            println("Secuencia jugador:" + miSecuencia)
            contadorJugador++
            comprobarSec()
        }
        botonRojo.setOnClickListener() {
            miSecuencia.add("Rojo")
            println("Secuencia jugador:" + miSecuencia)
            contadorJugador++
            comprobarSec()
        }
        botonVerde.setOnClickListener() {
            miSecuencia.add("Verde")
            println("Secuencia jugador:" + miSecuencia)
            contadorJugador++
            comprobarSec()
        }

        println(miSecuencia)
    }

    //Hago un string para enviar el contador por pantalla
    var string: String = ""
    fun comprobarSec() {
        var sumador: Int = 0

        if (contador == 5) {
            if (miSecuencia == colores) {
                sumador = sumador + 1
                string = sumador.toString()
                val rondas: TextView = findViewById(R.id.textView)
                rondas.setText(string)
                Toast.makeText(this, "Acertaste", Toast.LENGTH_SHORT).show()
                inicioPartida()
            } else if (miSecuencia != colores && contadorJugador==5){
                Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show()
                inicioPartida()
            }
        }
    }

}



