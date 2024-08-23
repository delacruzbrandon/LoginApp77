package com.clutchit.loginapp77.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.clutchit.loginapp77.navigation.HOME_GRAPH_ROUTE
import com.clutchit.loginapp77.navigation.Screen
import com.clutchit.loginapp77.ui.screens.welcome.WelcomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Welcome.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Welcome.route,
            arguments = listOf(navArgument("token") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val token = navBackStackEntry.arguments?.getString("token")
            WelcomeScreen(navController = navController, token = token.toString())
        }

    }
}