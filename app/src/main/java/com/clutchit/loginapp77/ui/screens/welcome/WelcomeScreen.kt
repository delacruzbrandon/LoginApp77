package com.clutchit.loginapp77.ui.screens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.clutchit.loginapp77.R
import com.clutchit.loginapp77.ui.screens.components.TopAppBar77
import com.clutchit.loginapp77.ui.theme.LoginApp77Theme


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar77(stringResource(id = R.string.login)) },
        content = { innerPadding ->
            WelcomeText(modifier.padding(innerPadding),
                name = "Android"
            )
        }
    )
}

@Composable
fun WelcomeText(
    modifier: Modifier,
    name: String
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Welcome, $name")
    }
}