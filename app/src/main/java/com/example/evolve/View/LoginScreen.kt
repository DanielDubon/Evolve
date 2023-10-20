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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evolve.Model.PersonApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(app: PersonApp,navcontroller: NavController ) {

    val context = LocalContext.current

    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

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
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))

Row {
    val coroutineScope2 = rememberCoroutineScope()
    Button(onClick = {

        coroutineScope2.launch(Dispatchers.IO) {  // Use Dispatchers.IO for database operations
            if (app.room.personDao().searchUser(username.value.text, password.value.text)){
                launch(Dispatchers.Main) {
                    navcontroller.navigate("Home/${username.value.text}")
                }

            }else{
                launch(Dispatchers.Main) {

                    Toast.makeText(
                        context,
                        "Contraseña o usuario incorrecto",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }







        }




    }) {
        Text("Ingresar")
    }
    Button(onClick = {
        navcontroller.navigate("Register")
    }) {
        Text("Registrarse")

    }
}

    }

}