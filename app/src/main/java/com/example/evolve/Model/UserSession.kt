package com.example.evolve.Model

import android.content.Context

object UserSession {
    var userId: Int? = null
    var username: String? = null
    var password: String? = null
    var age: Int? = null
    var weight: Int? = null
    var height: Int? = null


    fun clearSession() {
        userId = null
        username = null
        password = null
        age = null
        weight = null
        height = null

    }

    fun setUserDetails(user: Person) {
        userId = user.id
        username = user.name
        password = user.password
        age = user.age
        weight = user.weight
        height = user.Height

    }

    private const val PREFS_NAME = "UserSessionPrefs"
    private const val IS_LOGGED_IN = "isLoggedIn"
    fun saveLoginState(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun logout(context: Context) {
        userId = null
        username = null
        password = null
        age = null
        weight = null
        height = null

        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.apply()
    }


    private var currentUser: Person? = null



    fun getCurrentUser(): Person? {
        return currentUser
    }


}