package com.blaise.firebase.ui.theme.screens.products

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.firebase.data.productviewmodel
import com.blaise.firebase.navigation.ROUTE_VIEW_PRODUCT

@Composable
fun AddProductsScreen(navController: NavHostController) {
    Column (modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current
        Text(text = "Add Product",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline)

        var productName by remember { mutableStateOf(TextFieldValue("")) }
        var productQuantity by remember { mutableStateOf(TextFieldValue("")) }
        var productPrice by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            shape = RoundedCornerShape(16.dp),
            label = {Text(text = "Product name *",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = productQuantity,
            onValueChange = { productQuantity = it },
            shape = RoundedCornerShape(16.dp),
            label = {Text(text = "Product quantity *",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = "Product price *",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            val productRepository = productviewmodel(navController,context)
            productRepository.saveProduct(productName.text.trim(),productQuantity.text.trim(),
                productPrice.text)
            navController.navigate(ROUTE_VIEW_PRODUCT)},
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(2.dp, Color.Red)) {
            Text(text = "Save",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        //---------------------IMAGE PICKER START-----------------------------------//

        ImagePicker(Modifier,context, navController, productName.text.trim(), productQuantity.text.trim(), productPrice.text.trim())

        //---------------------IMAGE PICKER END-----------------------------------//

    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, quantity:String, price:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image") }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = { imagePicker.launch("image/*") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(2.dp, Color.Red)) {
                Text(text = "Select Image",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp) }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                val productRepository = productviewmodel(navController,context)
                productRepository.saveProductWithImage(name, quantity, price,imageUri!!)},
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(2.dp, Color.Red)) {
                Text(text = "Upload",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp) }
        }
    }
}

@Preview
@Composable
private fun AddProductsPreview() {
    AddProductsScreen(rememberNavController())
}