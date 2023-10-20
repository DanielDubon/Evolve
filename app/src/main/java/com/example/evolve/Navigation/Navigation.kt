package com.example.evolve.Navigation



import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.evolve.Model.PeopleDb
import com.example.evolve.Model.PersonApp
import com.example.evolve.View.LoginScreen
import com.example.evolve.View.RegisterScreen
import com.example.evolve.WelcomeScreen


@Composable
fun Navigation(app: PersonApp, modifier: Modifier = Modifier) {




    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Login.route,
        modifier = modifier) {
        composable(route = NavigationState.Home.route) {
                backStackEntry ->
            val Name = backStackEntry.arguments?.getString("username")
            if (Name != null) {
                WelcomeScreen(navController = navController,userName = Name)
            }

        }

        composable(route = NavigationState.Login.route) {
            LoginScreen(app ,navcontroller = navController)

        }


        composable(route = NavigationState.Register.route) {
            RegisterScreen(app ,navcontroller = navController)
        }
        
        composable(route = "${NavigationState.Detail.route}/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            if (mealId != null) {
                // MealDetailScreen(mealId, navController)
            }

        }}}

