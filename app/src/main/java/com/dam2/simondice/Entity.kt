package com.dam2.simondice

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val id: Int,
    var Ronda: Int,
)