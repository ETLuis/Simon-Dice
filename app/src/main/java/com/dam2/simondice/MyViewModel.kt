package com.dam2.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import kotlin.random.Random

class MyViewModel(application:Application) : AndroidViewModel(application) {

    // Etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"
    // Lista para la variable de la ronda
    var ronda = MutableLiveData<Int>()
    // Lista para la variable de la record
    var record = MutableLiveData<Int>()
    // Instancio una MutableLiveData
    val livedata_ronda= MutableLiveData<MutableList<Int>>()
    // Creamos un contexto de la aplicación para el builder
    private val context = getApplication<Application>().applicationContext
    // Creo una variable
    private var room : AppDataBase? = null

    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        room = Room
            .databaseBuilder(context,
                AppDataBase::class.java, "records")
            .build()

        val courutinaRoom = GlobalScope.launch(Dispatchers.Main) {
            try {
                record.value = room!!.userDao().getRonda()
            } catch(ex : NullPointerException) {
                room!!.userDao().crearRonda()
                record.value = room!!.userDao().getRonda()
            }
        }
    }

    fun actualizarRecord() {
        record.value = ronda.value
        val updateCorrutine = GlobalScope.launch(Dispatchers.Main) {
                room!!.userDao().update(User(1, ronda.value!!))
        }
        updateCorrutine.start()
    }

    fun reiniciaRecord() {
        val resetCorrutine = GlobalScope.launch(Dispatchers.Main) {
            room!!.userDao().update(User(1, 0))
            record.value = room!!.userDao().getRonda()
        }
        resetCorrutine.start()
    }

    fun sumarRonda(){
        ronda.value = ronda.value?.plus(1)
    }
    fun reiniciarRonda() {
        ronda.value = 0
    }
}