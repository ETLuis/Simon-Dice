package com.dam2.simondice

import androidx.room.*

@Dao
interface UserDao {

    //Recojo la tabla
    @Query("SELECT * FROM mi_tabla ORDER BY Ronda DESC")
    suspend fun getAllQuotes():List<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<Entity>)
}