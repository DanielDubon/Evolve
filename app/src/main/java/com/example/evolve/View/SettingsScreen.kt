package com.example.evolve.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evolve.Model.UserSession
import com.example.evolve.Navigation.NavigationState
import com.example.evolve.R

@Composable
fun SettingsScreen(navController: NavController) {
    val logoColor = colorResource(id = R.color.LogoColor)

    var selectedTab by remember { mutableStateOf(2) }

    // Variables para controlar las opciones de configuración
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("Español") }
    val languageOptions = listOf("Español", "Inglés")

    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(logoColor)
                .padding(8.dp)
        ) {
            Text(
                text = "Configuración",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(0.dp))

        // Notificaciones
        SettingOption(
            "Notificaciones",
            isNotificationsEnabled,
            onToggle = { isChecked ->
                isNotificationsEnabled = isChecked
                // Aquí puedes agregar la lógica para mostrar/ocultar notificaciones
            }
        )

        // Modo Oscuro
        SettingOption(
            "Modo Oscuro",
            isDarkModeEnabled,
            onToggle = { isChecked ->
                isDarkModeEnabled = isChecked
                // Aquí puedes agregar la lógica para cambiar el modo oscuro
            }
        )

        // Selección de Idioma
        SettingDropdown(
            "Idioma",
            selectedLanguage,
            languageOptions,
            onLanguageSelected = { language ->
                selectedLanguage = language
                // Implementa aquí la lógica para cambiar el idioma
            }
        )

        // Opciones adicionales
        SettingItem("Editar Perfil", Icons.Default.AccountCircle, 36.dp)
        SettingItem("Acerca de", Icons.Default.Info, 36.dp)
        SettingItem("Términos y Condiciones", Icons.Default.Create, 36.dp)
        SettingItem("Política de Privacidad", Icons.Default.Lock, 36.dp)
        SettingItem("Ayuda", Icons.Default.Search, 36.dp)
        SettingItem("Compartir APP", Icons.Default.Share, 36.dp)
        SettingItem("Comentarios", Icons.Default.Email, 36.dp)
        SettingItem("Calificanos!", Icons.Default.ThumbUp, 36.dp)
        SettingItem("Contáctanos", Icons.Default.Phone, 36.dp)

        // Botón de Cerrar Sesión
        Button(
            onClick = {
                // Agregar aquí la lógica para cerrar la sesión
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Cerrar Sesión", color = Color.White)
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
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home"
                    )
                },
                label = { Text("Home") },
                selected = selectedTab == 0,
                onClick = {
                    selectedTab = 0
                    // Navegar de regreso a la pantalla principal (Home)
                    navController.navigate(NavigationState.Home.route)
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Progress"
                    )
                },
                label = { Text("Progress") },
                selected = selectedTab == 1,
                onClick = {
                    selectedTab = 1
                    // Navegación a la pantalla de progreso
                    navController.navigate("Progress")
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
                label = { Text("Settings") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2
                    navController.navigate("Settings")
                }
            )
        }
    }
}

@Composable
private fun SettingOption(
    label: String,
    isChecked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Surface(
        color = Color(0xFF5744e6).copy(alpha = 0.1f),
        modifier = Modifier.fillMaxWidth(),
        contentColor = Color.Black
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isChecked,
                onCheckedChange = onToggle
            )
        }
    }
}

@Composable
private fun SettingDropdown(
    label: String,
    selectedValue: String,
    options: List<String>,
    onLanguageSelected: (String) -> Unit
) {
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }
    Surface(
        color = Color(0xFF5744e6).copy(alpha = 0.1f),
        modifier = Modifier.fillMaxWidth(),
        contentColor = Color.Black
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.clickable(
                    onClick = {
                        // Al hacer clic, expande o colapsa el menú desplegable
                        isLanguageMenuExpanded = !isLanguageMenuExpanded
                    }
                )
            ) {
                Text(text = selectedValue)
            }

            // Menú desplegable para idioma
            if (isLanguageMenuExpanded) {
                DropdownMenu(
                    expanded = isLanguageMenuExpanded,
                    onDismissRequest = {
                        isLanguageMenuExpanded = false
                    }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(onClick = {
                            onLanguageSelected(option)
                            isLanguageMenuExpanded = false
                            // Implementa aquí la lógica para cambiar el idioma
                        }) {
                            Text(text = option)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingItem(label: String, icon: ImageVector, iconSize: Dp) {
    Surface(
        color = Color(0xFF5744e6).copy(alpha = 0.1f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    // Agrega aquí la lógica para manejar la selección de la opción del menú
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF000000), // Color del icono
                modifier = Modifier.size(iconSize) // Tamaño del icono
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, color = Color(0xFF000000))
        }
    }
}