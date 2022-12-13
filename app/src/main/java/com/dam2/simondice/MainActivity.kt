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
/*import androidx.room.Room*/

class MainActivity : AppCompatActivity() {
    //Contador para comprobar la secuencia
    var contador: Int = 0
    var contadorJugador: Int = 0
    private val TAG_LOG: String = "mensaje Main"
    var textRecord: TextView = findViewById(R.id.textRandom)
    val rondas: TextView = findViewById(R.id.textView)
    var recordText: TextView = findViewById(R.id.textRandom)


    private var record = 0
    private val miViewModelo by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        //Hago una courrutina para la base de datos porque no se puede ejecutar en el hilo principal
        val CourutinaRoom = GlobalScope.launch(Dispatchers.Main) {
            //Instancio la base de datos
            val room: AppDataBase = Room
                .databaseBuilder(applicationContext,
                    AppDataBase::class.java, "records")
                .build()

            //Si es la primera vez que se hace le doy un valor a la ronda
            try {
                record = room.userDao().getRonda()
            } catch(ex : IndexOutOfBoundsException) {
                room.userDao().crearRonda()
                record = room.userDao().getRonda()
            }

        }
        //Inicio la courrutina
        CourutinaRoom.start()
*/

        val botonInicio: Button = findViewById(R.id.button7)

        botonInicio.setOnClickListener() {
            Toast.makeText(this, "Has iniciado partida", Toast.LENGTH_SHORT).show()
            textRecord.text = miViewModelo.ronda.value.toString()
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

        textRecord.text = miViewModelo.ronda.value.toString()


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
            if (miSecuencia == colores) {
                /*sumador = sumador + 1
                string = sumador.toString()
                rondas.setText(string)
                randomSec = randomSec + 1*/
                Toast.makeText(this, "Acertaste", Toast.LENGTH_SHORT).show()
                ronda()
                record()
                inicioPartida()

            } else if (miSecuencia != colores && contadorJugador==randomSec){
                Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show()
                rondas.setText("0")
                contador=5
                randomSec=5
                sumador=0

                rondaReinicio()
                inicioPartida()
            }
        }
    }

    fun ronda() {
        val miModelo by viewModels<MyViewModel>()

        miModelo.record.observe(this, Observer {
            rondas.text = miViewModelo.sumarRonda().toString()
        })
    }

    fun record() {
        val miModelo by viewModels<MyViewModel>()

        miModelo.record.observe(this, Observer {
            recordText.text = miViewModelo.sumarRecord().toString()
        })
    }

    fun rondaReinicio() {
        val miModelo by viewModels<MyViewModel>()

        miModelo.record.observe(this, Observer {
            rondas.text = miViewModelo.reiniciarRonda().toString()
        })
    }

    fun recordReinicio() {
        val miModelo by viewModels<MyViewModel>()

        miModelo.record.observe(this, Observer {
            recordText.text = miViewModelo.reiniciarRecord().toString()
        })
    }

}