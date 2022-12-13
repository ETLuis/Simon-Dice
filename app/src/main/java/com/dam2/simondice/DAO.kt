package com.dam2.simondice

import androidx.room.*

@Dao
interface UserDao {

    //Recojo la tabla
    @Query("SELECT * FROM mi_tabla ORDER BY Ronda DESC")
    suspend fun getAllUser():List<Entity>

    @Query("SELECT ronda FROM mi_tabla")
    suspend fun getRonda():Int

    @Query("SELECT record FROM mi_tabla")
    suspend fun getRecord():Int

    @Query("INSERT INTO mi_tabla (Ronda) VALUES (0)")
    suspend fun crearRonda()

    @Query("INSERT INTO mi_tabla (Record) VALUES (0)")
    suspend fun crearRecord()


    @Update
    suspend fun update(ronda: User)
}