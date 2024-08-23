package com.clutchit.loginapp77.ui.screens.auth.register

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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier,
        topBar = { TopAppBar77(stringResource(id = R.string.register)) },
        content = { innerPadding ->
            RegisterContent(
                paddingValues = innerPadding,
                navigateToLogin = { navController.navigate(Screen.Login.route) },
                snackbarHostState = snackbarHostState
            )
        }
    )
}