package com.clutchit.loginapp77

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clutchit.loginapp77.navigation.Screen
import com.clutchit.loginapp77.navigation.nav_graph.SetupNavGraph
import com.clutchit.loginapp77.ui.screens.auth.login.LoginScreen
import com.clutchit.loginapp77.ui.screens.welcome.WelcomeScreen
import com.clutchit.loginapp77.ui.theme.LoginApp77Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginApp77Theme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}