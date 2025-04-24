package com.blaise.firebase.ui.theme.screens.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.firebase.data.AuthViewModel
import com.blaise.firebase.navigation.ROUTE_LOGIN


@Composable
fun Register_Screen(navController:NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Register here",
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text(text = "Enter Email",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(Icons.Default.Email,
                contentDescription = "email") },
            keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =pass , onValueChange = {pass=it},
            label = { Text(text = "Enter password",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {Icon(Icons.Default.Lock,
                contentDescription = "passsword")},
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =confirmpass , onValueChange = {
            confirmpass=it},
            label = { Text(text = "Enter Confirmation Pass",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {Icon(Icons.Default.CheckCircle,
                contentDescription = "confirmpass")},
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button (onClick = {
            val myregister= AuthViewModel(navController,context)
            myregister.signup(email.text.trim(),pass.text.trim(),confirmpass.text.trim()) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp, Color.White),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "Register ",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Already have an account?",
            color = Color.Red,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp)

        Text(text = "Click to Login",
            modifier = Modifier.clickable { ROUTE_LOGIN },
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic)


    }



}

@Preview
@Composable
fun Register() {
    Register_Screen(rememberNavController())

}