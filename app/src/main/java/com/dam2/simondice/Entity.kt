package com.dam2.simondice

import androidx.room.*

@Entity(tableName = "mi_tabla")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "Ronda") val Ronda: Int,
    @ColumnInfo(name = "Record") val Record: Int,
)