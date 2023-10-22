package com.example.evolve.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    var weight: Int,
    var Height: Int,
    val password: String,
)