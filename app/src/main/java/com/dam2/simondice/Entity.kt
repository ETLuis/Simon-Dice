package com.dam2.simondice

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val id: Int,
    val Nombre: String?,
    var Ronda: Int,
    val Record: Int
)