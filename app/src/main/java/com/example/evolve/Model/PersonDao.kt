package com.example.evolve.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM Person")
     fun getAll(): List<Person>

    @Query("SELECT * FROM Person WHERE name=:name AND password=:password")
     fun searchUser(name: String, password: String): Boolean

    @Insert
     fun insertUser(people: List<Person>)

}