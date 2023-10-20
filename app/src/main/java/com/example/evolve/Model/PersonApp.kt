package com.example.evolve.Model

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room

class PersonApp: Application() {
    lateinit var room: PeopleDb

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(this, PeopleDb::class.java, "person").build()
    }
}