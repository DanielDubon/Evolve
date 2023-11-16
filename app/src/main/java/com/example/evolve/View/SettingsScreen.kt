package com.example.evolve.View

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evolve.Model.UserSession
import com.example.evolve.Navigation.NavigationState
import com.example.evolve.R
import java.util.Locale

@Composable
fun SettingsScreen(navController: NavController) {


    //Strings
    val settingsTitle = stringResource(id = R.string.settings_title)
    val notificationsLabel = stringResource(id = R.string.notifications)
    val darkModeLabel = stringResource(id = R.string.dark_mode)
    val languageLabel = stringResource(id = R.string.language)
    val SpanishLabel = stringResource(id = R.string.Spanish)
    val EnglishLabel = stringResource(id = R.string.English)
    val EditProfileLabel = stringResource(id = R.string.edit_profile)
    val AboutLabel = stringResource(id = R.string.about)
    val TermsLabel = stringResource(id = R.string.terms_and_conditions)
    val PrivacyLabel = stringResource(id = R.string.privacy_policy)
    val HelpLabel = stringResource(id = R.string.help)
    val ShareLabel = stringResource(id = R.string.share_app)
    val FeedBaackLabel = stringResource(id = R.string.feedback)
    val RateLabel = stringResource(id = R.string.rate_us)
    val ContactUsLabel = stringResource(id = R.string.contact_us)
    val SignOutLabel = stringResource(id = R.string.Sign_out)
    val ProgressLabel = stringResource(id = R.string.progress)
    val HomeLabel = stringResource(id = R.string.HOME)

    val logoColor = colorResource(id = R.color.LogoColor)

    var selectedTab by remember { mutableStateOf(2) }

    // Variables para controlar las opciones de configuración
    var isLanguageMenuExpanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("...") }
    val languageOptions = listOf(SpanishLabel, EnglishLabel)

    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(false) }


    fun setLocale(activity: Activity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

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
                text = settingsTitle,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(0.dp))

        // Notificaciones
        SettingOption(
            notificationsLabel,
            isNotificationsEnabled,
            onToggle = { isChecked ->
                isNotificationsEnabled = isChecked
                // Aquí puedes agregar la lógica para mostrar/ocultar notificaciones
            }
        )

        // Modo Oscuro
        SettingOption(
            darkModeLabel,
            isDarkModeEnabled,
            onToggle = { isChecked ->
                isDarkModeEnabled = isChecked
                // Aquí puedes agregar la lógica para cambiar el modo oscuro
            }
        )

        val context = LocalContext.current

        SettingDropdown(
            languageLabel,
            selectedLanguage,
            languageOptions,
            onLanguageSelected = { language ->
                selectedLanguage = language
                if (language == SpanishLabel){

                    (context as? Activity)?.let { activity ->
                        setLocale(activity, "es")
                        activity.recreate()
                    }
                }else{
                    (context as? Activity)?.let { activity ->
                        setLocale(activity, "en")
                        activity.recreate()
                    }
                }
            }
        )

        // Opciones adicionales
        SettingItem(EditProfileLabel, Icons.Default.AccountCircle, 36.dp)
        SettingItem(AboutLabel, Icons.Default.Info, 36.dp)
        SettingItem(TermsLabel, Icons.Default.Create, 36.dp)
        SettingItem(PrivacyLabel, Icons.Default.Lock, 36.dp)
        SettingItem(HelpLabel, Icons.Default.Search, 36.dp)
        SettingItem(ShareLabel, Icons.Default.Share, 36.dp)
        SettingItem(FeedBaackLabel, Icons.Default.Email, 36.dp)
        SettingItem(RateLabel, Icons.Default.ThumbUp, 36.dp)
        SettingItem(ContactUsLabel, Icons.Default.Phone, 36.dp)

        // Botón de Cerrar Sesión
        Button(
            onClick = {
              UserSession.logout(context)
                navController.navigate("Login")


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = SignOutLabel, color = Color.White)
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
                label = { Text(HomeLabel) },
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
                label = { Text(ProgressLabel) },
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
                label = { Text(settingsTitle) },
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