package com.dam2.simondice

import androidx.room.*

@Dao
interface UserDao {

    //Recojo la tabla
    @Query("SELECT Ronda FROM User WHERE id = 1")
    suspend fun getRonda():Int

    @Query("INSERT INTO User (Ronda) VALUES (0)")
    suspend fun crearRonda()

    @Update
    suspend fun update(record: User)
}