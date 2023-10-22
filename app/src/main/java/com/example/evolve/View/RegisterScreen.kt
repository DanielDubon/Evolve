package com.example.evolve.View

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evolve.Model.Person
import com.example.evolve.Model.PersonApp
import com.example.evolve.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(app: PersonApp, navcontroller: NavController){

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val username = remember { mutableStateOf(TextFieldValue()) }
    val weight = remember { mutableStateOf(TextFieldValue()) }
    val age = remember { mutableStateOf(TextFieldValue()) }
    val height = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val password2 = remember { mutableStateOf(TextFieldValue()) }
    val LogoPainter = painterResource(id = R.drawable.evolve)
    val logoColor = colorResource(id = R.color.LogoColor)
    val brush = Brush.linearGradient(
        colors = listOf(
            logoColor,
            Color.Black
        ),

        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            contentAlignment = Alignment.Center ,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)



        ) {
            Image(
                painter = LogoPainter,
                contentDescription = "Logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Nombre") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text("Edad") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = height.value,
            onValueChange = { height.value = it },
            label = { Text("Altura(cm)") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = weight.value,
            onValueChange = { weight.value = it },
            label = { Text("Peso(kg)") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password2.value,
            onValueChange = { password2.value = it },
            label = { Text("Repite tu contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))


        Row {

            val coroutineScope = rememberCoroutineScope()
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = logoColor),
                onClick = {
                    try {


                        if (password.value.text != "" && username.value.text != "" && age.value.text != "" && weight.value.text != "" && height.value.text != "") {
                            if (password.value.text == password2.value.text) {
                                val nombre = username.value.text
                                val edad = age.value.text.toInt()
                                val peso = weight.value.text.toInt()
                                val altura = height.value.text.toInt()
                                val contraseña = password.value.text


                                coroutineScope.launch(Dispatchers.IO) {  // Use Dispatchers.IO for database operations
                                    val person = Person(
                                        id = 1,
                                        name = nombre,
                                        Height = altura,
                                        age = edad,
                                        weight = peso,
                                        password = contraseña
                                    )
                                    app.room.personDao().insertUser(listOf(person))

                                    val people = app.room.personDao().getAll()
                                    Log.d("BASEDEDATOSXXX", "HAY: ${people.size} UWU")

                                    // Switch back to the main thread to interact with the UI
                                    launch(Dispatchers.Main) {
                                        navcontroller.navigate("Login")
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "LAS CONTRASEÑAS NO SON IGUALES",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {
                            Toast.makeText(
                                context,
                                "Completa todos los campos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }catch (e: Exception){
                        Toast.makeText(
                            context,
                            "Verifica los datos de los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Text("Registrarse")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = logoColor ),
                onClick = {
                navcontroller.navigate("Login")
            }) {
                Text("¿Ya tienes cuenta?")
            }
        }

    }
}

