package com.example.evolve.Navigation

sealed class NavigationState(val route: String) {
    object Home: NavigationState("Login")
    object Login: NavigationState("Login")
    object Register: NavigationState("Register")
    object Detail: NavigationState("Detail")
    object Profile: NavigationState("Profile")
    object Settings: NavigationState("Settings")
}