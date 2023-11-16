package com.example.evolve.View

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
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

    //Strings
    val HiLabel = stringResource(id = R.string.hi)
    val YourProgressLabel = stringResource(id = R.string.your_progress)
    val BMILabel = stringResource(id = R.string.BMI)
    val BMICategoryLabel = stringResource(id = R.string.BMI_category)
    val ProgressLabel = stringResource(id = R.string.progress)
    val HomeLabel = stringResource(id = R.string.HOME)
    val settingsTitle = stringResource(id = R.string.settings_title)


    val logoColor = colorResource(id = R.color.LogoColor)
    val username = UserSession.username ?: "DefaultUsername"
    val peso = UserSession.weight ?: 0
    val altura = UserSession.height ?: 0

    val bmi = calculateBMI(peso, altura / 100.0) // Convertir altura a metros
    val bmiCategory = getBMICategory(LocalContext.current,bmi)
    val motivationMessage = getMotivationMessage(LocalContext.current ,bmiCategory)

    val bmiIdeal = 22.0 // Define el IMC ideal

    val progress = (bmi / bmiIdeal).coerceIn(0.0, 1.0) // Calcula el progreso (limitado entre 0 y 1)

    var selectedTab by remember { mutableStateOf(1) as MutableState<Int> } // Seleccionar la pestaña "Progress" por defecto

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
                text = "$HiLabel $username",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = YourProgressLabel,
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
                ListItem(BMILabel, "$bmi")
                ListItem(BMICategoryLabel, bmiCategory)
                ListItem(" ", motivationMessage)
                Spacer(modifier = Modifier.height(16.dp))

                // Barra de progreso
                ProgressBar(progress)
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
                label = { androidx.compose.material3.Text(HomeLabel) },
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
                label = { androidx.compose.material3.Text(ProgressLabel) },
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
                label = { androidx.compose.material3.Text(settingsTitle) },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2
                    navController.navigate("Settings")
                }
            )
        }
    }
}

@Composable
fun ProgressBar(progress: Double) {
    val ProgressLabel = stringResource(id = R.string.progress)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        LinearProgressIndicator(
            progress = progress.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "$ProgressLabel: ${(progress * 100).toInt()}%",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
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
fun getBMICategory(context: Context, bmi: Double): String {
    val resources = context.resources
    val LowWeightLabel = resources.getString(R.string.Low_weight)
    val NormalWeightLabel = resources.getString(R.string.Normal_weight)
    val OverweightLabel = resources.getString(R.string.overweight)
    val ObeseLabel = resources.getString(R.string.Obese)

    return when {

        bmi < 18.5 -> LowWeightLabel
        bmi < 25.0 -> NormalWeightLabel
        bmi < 30.0 -> OverweightLabel
        else -> ObeseLabel
    }
}

// Función para obtener un mensaje de motivación basado en la categoría de IMC
fun getMotivationMessage(context: Context,bmiCategory: String): String {

    val resources = context.resources
    return when (bmiCategory) {
        "Bajo peso" -> resources.getString(R.string.bmi_low)
        "Normal" -> resources.getString(R.string.bmi_normal)
        "Sobrepeso" -> resources.getString(R.string.bmi_overweight)
        "Obesidad" -> resources.getString(R.string.bmi_obese)
        else -> resources.getString(R.string.bmi_else)
    }
}

