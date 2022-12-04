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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

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
    //Creo una variable para el record en Firebase
    private var Firebase_Record: DatabaseReference

    init {

        ronda.value = 0

        //Instacio Firebase
        Firebase_Record = Firebase.database("https://console.firebase.google.com/u/0/project/simon-dice-60d56/firestore/data/~2FUser~2Fr73qVGdyaw0TqziD6Vbm?hl=es").getReference("record")
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


        /*
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
         */
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