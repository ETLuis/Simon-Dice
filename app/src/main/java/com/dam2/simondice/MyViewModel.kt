package com.dam2.simondice

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import com.google.firebase.database.DatabaseReference

class MyViewModel(application: Application): AndroidViewModel(application) {

    // Etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"
    // Lista para la variable de la ronda
    val ronda = MutableLiveData<Int>()
    // Lista para la variable de la record
    val record = MutableLiveData<Int>()

    // Creamos un contexto de la aplicación para el builder
    private val context = getApplication<Application>().applicationContext
    // Creo una variable
    private var room : AppDataBase? = null
    //Creo una variable para el record en Firebase
    private lateinit var Firebase_Record: DatabaseReference

    init {

        ronda.value = 0
        record.value = 0
  /*
        //Instacio Firebase
        Firebase_Record = Firebase.database("https://simon-dice-60d56-default-rtdb.firebaseio.com/").getReference("record")
        //definición del listener
        val recordListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                record.value = dataSnapshot.getValue<Int>()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d(tag, "recordListener:OnCancelled", error.toException())
            }
        }
        //Añado el listener a la BD
        Firebase_Record.addValueEventListener(recordListener)
*/


        Log.d(TAG_LOG, "Inicializamos livedata")
        room = Room.databaseBuilder(context, AppDataBase::class.java, "UserDao").build()

        val courutinaRoom = GlobalScope.launch(Dispatchers.Main) {
            try {
                record.value = room!!.userDao().getRonda()
            } catch(error : NullPointerException) {
                room!!.userDao().crearRonda()
                record.value = room!!.userDao().getRonda()

            }
        }
        courutinaRoom.start()

    }

    fun actualizarRecord() {
        record.value = ronda.value
        val actualizarCorrutine = GlobalScope.launch(Dispatchers.Main) {
            room!!.userDao().update(User(1, ronda.value!!, record.value!!))
        }
        actualizarCorrutine.start()
    }

    fun reiniciaRecord() {
        val resetCorrutine = GlobalScope.launch(Dispatchers.Main) {
            room!!.userDao().update(User(1, 0, 0))
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
    fun sumarRecord(){
        record.value = record.value?.plus(1)
    }
    fun reiniciarRecord() {
        record.value = 0
    }
}