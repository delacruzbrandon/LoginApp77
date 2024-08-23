package com.clutchit.loginapp77.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.clutchit.loginapp77.navigation.AUTH_GRAPH_ROUTE
import com.clutchit.loginapp77.navigation.ROOT_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE,
    ) {
        homeNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}


