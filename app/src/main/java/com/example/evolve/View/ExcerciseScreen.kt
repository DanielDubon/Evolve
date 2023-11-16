package com.example.evolve.View

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext

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
                    ExerciseCard(exercise, navController, LocalContext.current)
                }
            }

        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, navController: NavController, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, YouTubePlayerActivity::class.java).apply {
                    putExtra("videoId", exercise.youtubeVideoId)
                }
                context.startActivity(intent)
            }
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

//YouTube
class YouTubePlayerActivity : ComponentActivity() {

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoId = intent.getStringExtra("videoId")

        if (videoId != null) {
            val youtubeUrl = "https://www.youtube.com/watch?v=$videoId"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
            launcher.launch(intent)
        } else {
            println("ERROR")
        }
    }
}

data class Exercise(
    val name: Int,
    val duration: String,
    val imageResId: Int,
    val youtubeVideoId: String
)

fun getExercisesForCategory(context: Context, category: String?): List<Exercise> {
    val categoryStrength = context.resources.getString(R.string.category_strength)
    val categoryEndurance = context.resources.getString(R.string.category_endurance)
    val categoryFlexibility = context.resources.getString(R.string.category_flexibility)
    if (category != null) {
        Log.d("CATEGORY",category)

    }
    Log.d("CHECK CATEGORY FUERZA", categoryStrength.toString())
    return when (category) {

        categoryStrength.toString() -> {
            listOf(
                Exercise(R.string.exercise_weights, "45 m", R.drawable.pesas,"RIKN90NZqMg"),
                Exercise(R.string.exercise_crossfit, "45 m", R.drawable.crossfit, "8szBUOMuUX8")

            )
        }
        categoryEndurance.toString() -> {
            listOf(
                Exercise(R.string.exercise_cardio, "20 m", R.drawable.cardio,"4mK5Q39jczI"),
                Exercise(R.string.exercise_hiit, "18 m", R.drawable.hiit, "9qlik98nX4w"),
                Exercise(R.string.exercise_calisthenics, "22 m", R.drawable.calistenia, "Bz8Vou63OUk")
            )
        }
        categoryFlexibility.toString() -> {
            listOf(
                Exercise(R.string.exercise_yoga, "15 m", R.drawable.yoga, "M5Jcq-YaMv4"),
                Exercise(R.string.exercise_zumba, "45 m", R.drawable.zumba, "kILnhdTbG3k"),
                Exercise(R.string.exercise_boxing, "45 m", R.drawable.boxeo, "TrDYJytaCdA")
            )
        }
        else -> emptyList()
    }
}
