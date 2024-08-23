package com.clutchit.loginapp77.navigation


const val ROOT_GRAPH_ROUTE = "root"
const val HOME_GRAPH_ROUTE = "home"
const val AUTH_GRAPH_ROUTE = "auth"

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
}