package com.dam2.simondice


import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MyViewModel() : ViewModel() {

    // Etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"
    // Lista para la secuencia random
    val numbers = mutableListOf<Int>()
    // Instancio una MutableLiveData
    val livedata_numbers = MutableLiveData<MutableList<Int>>()


    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_numbers.value = numbers
    }

    fun sumarRandom() {

        // añadimos entero random a la lista
        numbers.add(Random.nextInt(0,4))
        // actualizamos el livedata, de esta manera si hay un observador
        // este recibirá la nueva lista
        livedata_numbers.setValue(numbers)
        // la mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata:" + numbers.toString())
    }


}