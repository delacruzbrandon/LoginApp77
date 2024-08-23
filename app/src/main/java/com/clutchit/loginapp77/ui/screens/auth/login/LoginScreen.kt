package com.clutchit.loginapp77.ui.screens.auth.login

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.clutchit.loginapp77.R
import com.clutchit.loginapp77.navigation.Screen
import com.clutchit.loginapp77.ui.screens.components.TopAppBar77


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar77(stringResource(id = R.string.login)) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            LoginContent(
                paddingValues = innerPadding,
                navigateToWelcome = {
                    navController.popBackStack()
                    navController.navigate(route = Screen.Welcome.passUsername(it))
                },
                navigateToRegister = {
                    navController.navigate(route = Screen.Register.route)
                },
                snackbarHostState = snackbarHostState
            )
        }
    )
}