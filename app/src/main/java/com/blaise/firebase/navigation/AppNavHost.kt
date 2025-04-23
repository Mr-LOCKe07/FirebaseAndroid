package com.blaise.firebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blaise.firebase.ui.theme.screens.home.Home_Screen
import com.blaise.firebase.ui.theme.screens.login.Login_Screen
import com.blaise.firebase.ui.theme.screens.products.Products_Screen
import com.blaise.firebase.ui.theme.screens.register.Register_Screen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_HOME) {
    NavHost(navController = navController,
        modifier = Modifier,
        startDestination = startDestination){
        composable(ROUTE_HOME) {
            Home_Screen(navController)
        }
        composable(ROUTE_LOGIN){
            Login_Screen(navController)
        }
        composable(ROUTE_REGISTER){
            Register_Screen(navController)
        }
        composable(ROUTE_PRODUCTS){
            Products_Screen(navController)
        }
    }
}