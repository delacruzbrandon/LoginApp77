package com.clutchit.loginapp77.ui.screens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.clutchit.loginapp77.R
import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.ui.screens.components.TopAppBar77
import com.clutchit.loginapp77.ui.theme.LoginApp77Theme
import com.clutchit.loginapp77.ui.viewmodel.WelcomeViewModel
import com.clutchit.loginapp77.util.RequestState
import org.koin.androidx.compose.koinViewModel


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = koinViewModel(),
    token: String
) {
    val usernameState by welcomeViewModel.usernameState.collectAsState()

    var username by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = null) {
        welcomeViewModel.getUsername(token)
    }

    LaunchedEffect(key1 = usernameState) {
        when (usernameState) {
            is RequestState.Success -> {
                username = (usernameState as RequestState.Success).data.toString()
            }
            is RequestState.Loading -> {
                return@LaunchedEffect
            }
            is RequestState.Error -> {
                return@LaunchedEffect
            }
            RequestState.Idle -> {
                return@LaunchedEffect
            }
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar77(stringResource(id = R.string.login)) },
        content = { innerPadding ->
            WelcomeText(modifier.padding(innerPadding),
                name = username
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