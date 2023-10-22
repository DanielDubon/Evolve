package com.example.evolve.View

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.evolve.Model.PersonApp
import com.example.evolve.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(app: PersonApp,navcontroller: NavController ) {

    val context = LocalContext.current

    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

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
            .background(brush)
            .offset(y = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        val LogoPainter = painterResource(id = R.drawable.evolve)

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
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))

Row {
    val coroutineScope2 = rememberCoroutineScope()
    Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = logoColor )
        , onClick = {

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

    Spacer(modifier = Modifier.width(10.dp))

    Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = logoColor ), onClick = {
        navcontroller.navigate("Register")
    }) {
        Text("Registrarse")

    }
}

    }

}