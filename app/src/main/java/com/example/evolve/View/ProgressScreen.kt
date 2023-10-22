package com.example.evolve.View

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evolve.Model.PersonApp
import com.example.evolve.Model.UserSession
import com.example.evolve.Navigation.NavigationState
import com.example.evolve.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgressScreen(navController: NavController) {
    val logoColor = colorResource(id = R.color.LogoColor)
    val username = UserSession.username ?: "DefaultUsername"
    val peso = UserSession.weight ?: 0
    val altura = UserSession.height ?: 0

    val bmi = calculateBMI(peso, altura / 100.0) // Convertir altura a metros
    val bmiCategory = getBMICategory(bmi)
    val motivationMessage = getMotivationMessage(bmiCategory)


    var selectedTab by remember { mutableStateOf(1) as MutableState<Int> }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(logoColor)
                .padding(16.dp)
        ) {
            Text(
                text = "Hola, $username",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = "Tu Progreso",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = logoColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ListItem("Tu IMC:", "$bmi")
                ListItem("Categoría de IMC:", bmiCategory)
                ListItem(" ", motivationMessage)
                Spacer(modifier = Modifier.height(16.dp))

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .background(logoColor)
                .padding(0.dp, 24.dp, 0.dp, 0.dp),
            backgroundColor = Color(0xFF5744e6)
        ) {
            BottomNavigationItem(
                icon = {
                    androidx.compose.material3.Icon(
                        Icons.Default.Home,
                        contentDescription = "Home"
                    )
                },
                label = { androidx.compose.material3.Text("Home") },
                selected = selectedTab == 0,
                onClick = {
                    selectedTab = 0
                    // Navegar de regreso a la pantalla principal (Home)
                    navController.navigate(NavigationState.Home.route)
                }
            )
            BottomNavigationItem(
                icon = {
                    androidx.compose.material3.Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Progress"
                    )
                },
                label = { androidx.compose.material3.Text("Progress") },
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    // Navegación a la pantalla de progreso
                    navController.navigate("Progress")
                }
            )
            BottomNavigationItem(
                icon = {
                    androidx.compose.material3.Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
                label = { androidx.compose.material3.Text("Settings") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
    }
}


@Composable
fun ListItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value)
    }
}




// Función para calcular el IMC
fun calculateBMI(weight: Int, height: Double): Double {
    return weight / (height * height)
}

// Función para obtener la categoría de IMC
fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Bajo peso"
        bmi < 25.0 -> "Normal"
        bmi < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }
}

// Función para obtener un mensaje de motivación basado en la categoría de IMC
fun getMotivationMessage(bmiCategory: String): String {
    return when (bmiCategory) {
        "Bajo peso" -> "¡Es importante mantener una dieta equilibrada y saludable para alcanzar un peso saludable!"
        "Normal" -> "¡Felicidades! Tu peso está en un rango saludable. ¡Sigue cuidándote!"
        "Sobrepeso" -> "Puedes alcanzar un peso saludable con una dieta equilibrada y ejercicio regular."
        "Obesidad" -> "Es importante tomar medidas para mejorar tu salud. Consulta a un profesional médico."
        else -> "¡Sigue trabajando en tu salud y bienestar!"
    }
}

