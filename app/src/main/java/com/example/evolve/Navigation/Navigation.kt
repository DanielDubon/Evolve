package com.example.evolve.Navigation



import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evolve.View.LoginScreen
import com.example.evolve.View.RegisterScreen


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Home.route,
        modifier = modifier) {
        composable(route = NavigationState.Home.route) {
            LoginScreen(navcontroller = navController)

        }

        composable(route = NavigationState.Login.route) {
            LoginScreen(navcontroller = navController)

        }


        composable(route = NavigationState.Register.route) {
            RegisterScreen(navController = navController)
        }
        
        composable(route = "${NavigationState.Detail.route}/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            if (mealId != null) {
                // MealDetailScreen(mealId, navController)
            }

        }}}

