package com.example.evolve.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evolve.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    navController: NavController,
    category: String,
    exercises: List<Exercise>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF5744e6))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(exercises) { exercise ->
                    ExerciseCard(exercise)
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = exercise.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            val exerciseName = stringResource(id = exercise.name)
            Text(
                text = exerciseName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = exercise.duration,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
data class Exercise(
    val name: Int,
    val duration: String,
    val imageResId: Int
)

// Función de ejemplo que devuelve ejercicios basados en la categoría
fun getExercisesForCategory(category: String?): List<Exercise> {
    return when (category) {
        "Fuerza" -> {
            listOf(
                Exercise(R.string.exercise_weights, "45 m", R.drawable.pesas),
                Exercise(R.string.exercise_crossfit, "45 m", R.drawable.crossfit)

            )
        }
        "Resistencia" -> {
            listOf(
                Exercise(R.string.exercise_cardio, "20 m", R.drawable.cardio),
                Exercise(R.string.exercise_hiit, "18 m", R.drawable.hiit),
                Exercise(R.string.exercise_calisthenics, "22 m", R.drawable.calistenia)
            )
        }
        "Flexibilidad" -> {
            listOf(
                Exercise(R.string.exercise_yoga, "15 m", R.drawable.yoga),
                Exercise(R.string.exercise_zumba, "45 m", R.drawable.zumba),
                Exercise(R.string.exercise_boxing, "45 m", R.drawable.boxeo)
            )
        }
        else -> emptyList() //
    }
}
