package com.example.evolve.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val username = remember { mutableStateOf(TextFieldValue()) }
    val weight = remember { mutableStateOf(TextFieldValue()) }
    val age = remember { mutableStateOf(TextFieldValue()) }
    val height = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val password2 = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
            label = { Text("Altura(m)") },
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


            Button(

                onClick = {
                    if (password.value.text == password2.value.text) {
                        //navegar a pantalla principal
                    } else {
                        Toast.makeText(
                            context,
                            "LAS CONTRASEÑAS NO SON IGUALES",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }) {
                Text("Registrarse")
            }
            Button(onClick = {
                navController.navigate("Login")
            }) {
                Text("¿Ya tienes cuenta?")
            }
        }

    }
}

