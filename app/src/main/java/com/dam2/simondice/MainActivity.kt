package com.dam2.simondice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    //Contador para comprobar la secuencia
    var contador: Int = 0
    var contadorJugador: Int = 0
    private val TAG_LOG: String = "mensaje Main"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creo un viewModels y instancio un botón
        val miModelo by viewModels<MyViewModel>()
        val botonNuevoRandom: Button = findViewById(R.id.roll_button)

        botonNuevoRandom.setOnClickListener {
            // llamo a la función del ViewModel
            miModelo.sumarRandom()
            Log.d(TAG_LOG, "Actualizo ronda")
        }

        miModelo.livedata_numbers.observe(
            this,
            Observer(
                // funcion que cambia el numero
                fun(nuevaListaRandom: MutableList<Int>) {
                    var textRandom: TextView = findViewById(R.id.textRandom)
                    textRandom.text = nuevaListaRandom.toString()
                }
            )
        )



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
    var randomSec: Int = 5

    suspend fun visualizarSecuencia() {
        val botonAzul: Button = findViewById(R.id.button8)
        val botonAmarillo: Button = findViewById(R.id.button9)
        val botonRojo: Button = findViewById(R.id.button10)
        val botonVerde: Button = findViewById(R.id.button11)

        colores.clear()

        for (z in 1..randomSec) {
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
    var sumador: Int = 0
    fun comprobarSec() {

        if (contador == randomSec) {
            val rondas: TextView = findViewById(R.id.textView)
            if (miSecuencia == colores) {
                sumador = sumador + 1
                string = sumador.toString()
                rondas.setText(string)
                randomSec = randomSec + 1
                Toast.makeText(this, "Acertaste", Toast.LENGTH_SHORT).show()
                record()

            } else if (miSecuencia != colores && contadorJugador==randomSec){
                randomSec = randomSec + 1
                Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show()
                rondas.setText("0")
                contador=5
                randomSec=5
                sumador=0

                inicioPartida()
            }
        }
    }
    var recordNum: Int = 0
    fun record() {
        val record: TextView = findViewById(R.id.textView4)
        var string2: String = ""

        if(recordNum<=sumador) {
            recordNum = recordNum + 1
            string2 = recordNum.toString()
            record.setText(string2)
        }



        inicioPartida()
    }

}



