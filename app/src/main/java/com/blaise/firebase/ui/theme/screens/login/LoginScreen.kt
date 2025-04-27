package com.blaise.firebase.ui.theme.screens.login

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
import com.blaise.firebase.navigation.ROUTE_REGISTER

@Composable
fun Login_Screen(navController: NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(
            text = "Login here",
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = {
                Text(
                    text = "Enter Email",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
            },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "email"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = pass, onValueChange = { pass = it },
            label = {
                Text(
                    text = "Enter Password",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
            },
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "password"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val mylogin = AuthViewModel(navController, context)
                mylogin.login(email.text.trim(), pass.text.trim())
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Don't have account?",
            color = Color.Red,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp
        )

        Button(
            onClick = { navController.navigate(ROUTE_REGISTER) },
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Text(
                text = "Click to Register",
                modifier = Modifier.clickable { ROUTE_REGISTER },
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
        

    }


@Preview
@Composable
private fun LoginPreview() {
    Login_Screen(rememberNavController())
}