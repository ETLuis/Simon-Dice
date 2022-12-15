package com.dam2.simondice

import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "Ronda")
    val Ronda: Int,

    @ColumnInfo(name = "Record")
    val Record: Int
)