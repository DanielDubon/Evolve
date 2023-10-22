package com.example.evolve.Navigation



import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evolve.Model.PersonApp
import com.example.evolve.View.LoginScreen
import com.example.evolve.View.ProgressScreen
import com.example.evolve.View.RegisterScreen
import com.example.evolve.WelcomeScreen


@Composable
fun Navigation(app: PersonApp, modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    // Variables para almacenar los valores compartidos

    NavHost(navController = navController,
        startDestination = NavigationState.Login.route,
        modifier = modifier) {
        composable(route = NavigationState.Home.route) {



                WelcomeScreen(navController = navController)


        }

        composable(route = NavigationState.Login.route) {
            LoginScreen(app ,navcontroller = navController)

        }

        composable(route = NavigationState.Register.route) {
            RegisterScreen(app ,navcontroller = navController)
        }

        composable(route = NavigationState.Progress.route) {
            ProgressScreen(navController)
        }

        }
}


