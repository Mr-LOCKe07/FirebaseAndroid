package com.blaise.firebase.ui.theme.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.firebase.data.productviewmodel
import com.blaise.firebase.navigation.ROUTE_ADD_PRODUCT
import com.blaise.firebase.navigation.ROUTE_VIEW_PRODUCT
import com.blaise.firebase.navigation.ROUTE_VIEW_UPLOAD

@Composable
fun Home_Screen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val context= LocalContext.current
        var productdata = productviewmodel(navController,context)

        Text(text = "Welcome to the Home Page",
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { navController.navigate(ROUTE_ADD_PRODUCT) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "Add Product",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif) }

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { navController.navigate(ROUTE_VIEW_PRODUCT) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "View Product",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif) }

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { navController.navigate(ROUTE_VIEW_UPLOAD) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "View Products",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif)
        }


    }



}

@Preview
@Composable
private fun HomePreview() {
    Home_Screen(rememberNavController())
}