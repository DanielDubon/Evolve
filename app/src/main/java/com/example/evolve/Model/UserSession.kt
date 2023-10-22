package com.example.evolve.Model

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
        // ... establece otros campos si los agregas
    }
}