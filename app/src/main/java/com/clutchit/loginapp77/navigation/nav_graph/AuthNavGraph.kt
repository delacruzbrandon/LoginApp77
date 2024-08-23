package com.clutchit.loginapp77.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.clutchit.loginapp77.navigation.AUTH_GRAPH_ROUTE
import com.clutchit.loginapp77.navigation.Screen
import com.clutchit.loginapp77.ui.screens.auth.login.LoginScreen
import com.clutchit.loginapp77.ui.screens.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController = navController)
        }
    }
}