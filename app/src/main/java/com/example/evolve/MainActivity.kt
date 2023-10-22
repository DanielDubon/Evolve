package com.example.evolve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evolve.Model.PersonApp
import com.example.evolve.Model.UserSession
import com.example.evolve.Navigation.Navigation
import com.example.evolve.Navigation.NavigationState
import com.example.evolve.ui.theme.EvolveTheme
import kotlinx.coroutines.launch


class DarkModeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val app = this.application as PersonApp

            EvolveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(app)
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    val username = UserSession.username ?: "DefaultUsername"
    val logoColor = colorResource(id = R.color.LogoColor)
    var selectedTab by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF000000))
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.evolve),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(256.dp)
                    .padding(8.dp)
            )
        }
        Text(
            text = "Bienvenid@, $username",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp, 0.dp, 0.dp),
        ) {
            Button(
                onClick = { /*...*/ },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Empezemos!", color = Color.White)
            }
        }
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .background(logoColor)
                .padding(0.dp, 24.dp, 0.dp, 0.dp),
            backgroundColor = Color(0xFF5744e6)
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = selectedTab == 0,
                onClick = {
                    selectedTab = 0
                    // Navegar de regreso a la pantalla principal (Home)
                    navController.navigate(NavigationState.Home.route)
                }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Favorite, contentDescription = "Progress") },
                label = { Text("Progress") },
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    // Navegación a la pantalla de progreso
                    navController.navigate("Progress")
                }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                label = { Text("Settings") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EvolveTheme {
    }
}