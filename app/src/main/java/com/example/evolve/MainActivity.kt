package com.example.evolve

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.compose.AsyncImage
import com.example.evolve.Model.PersonApp
import com.example.evolve.Model.UserSession
import com.example.evolve.Navigation.Navigation
import com.example.evolve.Navigation.NavigationState
import com.example.evolve.ui.theme.EvolveTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


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

@Composable
fun WelcomeScreen(navController: NavController) {

    val context = LocalContext.current

    val username = UserSession.username ?: "DefaultUsername"
    val logoColor = colorResource(id = R.color.LogoColor)
    var selectedTab by remember { mutableStateOf(0) }
    val images = listOf(
        "https://ccdfit.com/wp-content/uploads/2020/06/HIIT.jpg",
        "https://www.sabervivirtv.com/medio/2023/07/06/calistenia_43633bf1_916361888_230706101132_1280x720.jpg",
        "https://c.files.bbci.co.uk/AB44/production/_129744834_gettyimages-1403441643.jpg",
        "https://images.contentstack.io/v3/assets/blt45c082eaf9747747/blt8f8854d227bd0de8/5de0b9ae649e797790ff6b92/Content1_CardiovsStrength.jpg?width=1232&auto=webp&format=progressive&quality=76"
    )

    Column(
        modifier = Modifier.fillMaxSize()
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
                    .size(200.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        CarouselSlider(images, modifier = Modifier.weight(1f))
        Button(
            onClick = {
                // Manejar la acción del botón Empezar aquí y navegar a CategoriesScreen
                navController.navigate("Categories")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Empezemos!", color = Color.White)
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
                onClick = { selectedTab = 2
                    navController.navigate("Settings")
                }
            )
        }
    }
}

@Composable
fun CarouselSlider(images: List<String>, modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var index by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true, block = {
        coroutineScope.launch {
            while (true) {
                delay(3000) // Cambia de imagen cada 2 segundos
                if (index == images.size - 1) index = 0
                else index++
                scrollState.animateScrollToItem(index)
            }
        }
    })

    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            LazyRow(
                state = scrollState,
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(images) { index, image ->
                    Card(
                        modifier = Modifier.height(200.dp).width(375.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        AsyncImage(
                            model = image,
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


