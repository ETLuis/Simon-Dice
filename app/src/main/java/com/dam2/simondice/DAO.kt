package com.dam2.simondice

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT ronda FROM user where id=1") //Recojo todos los usuarios
    suspend fun getRonda(): Int

    @Query("INSERT INTO User (ronda) VALUES (0)")
    suspend fun crearRonda()

    @Update
    suspend fun update(record: User)
}