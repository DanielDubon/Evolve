package com.example.evolve.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evolve.Model.PersonApp
import com.example.evolve.Navigation.NavigationState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgressScreen(
    navController: NavController,
    userName: String,
    userWeight: Int,
    userHeight: Int
) {
    val bmi = calculateBMI(userWeight, userHeight / 100.0) // Convertir altura a metros
    val bmiCategory = getBMICategory(bmi)
    val motivationMessage = getMotivationMessage(bmiCategory)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hola, $userName",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = "Tu IMC: $bmi",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = "Categoría de IMC: $bmiCategory",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = motivationMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(315.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF000000))
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(NavigationState.Home.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Volver al inicio", color = Color.White)
            }
        }
    }
    // BottomNavigation?
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


