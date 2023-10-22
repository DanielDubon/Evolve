package com.example.evolve.Navigation

sealed class NavigationState(val route: String) {
    object Home: NavigationState("Home/{username}")
    object Login: NavigationState("Login")
    object Register: NavigationState("Register")
    object Progress: NavigationState("Progress")
    object Profile: NavigationState("Profile")
    object Settings: NavigationState("Settings")
}