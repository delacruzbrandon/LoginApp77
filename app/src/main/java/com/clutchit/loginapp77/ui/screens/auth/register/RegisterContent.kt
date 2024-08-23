package com.clutchit.loginapp77.ui.screens.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.clutchit.loginapp77.R
import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.ui.screens.components.PasswordTextField77
import com.clutchit.loginapp77.ui.screens.components.UsernameTextField77
import com.clutchit.loginapp77.ui.viewmodel.RegisterViewModel
import com.clutchit.loginapp77.util.RequestState
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegisterContent(
    paddingValues: PaddingValues,
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    snackbarHostState: SnackbarHostState // TODO Use for errors
) {
    val modifier = Modifier

    val userRegisterState by viewModel.userRegisterState.collectAsState()
    val goLoginState by viewModel.goLogin.collectAsState()
    val usernameState by viewModel.username.collectAsState()
    val passwordState by viewModel.password.collectAsState()

    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = userRegisterState) {
        when (userRegisterState) {
            is RequestState.Success -> {
                snackbarHostState.showSnackbar(
                    message = "User Added. Success!"
                )
                navigateToLogin()
            }
            is RequestState.Loading -> {
                snackbarHostState.showSnackbar(
                    message = "Registering User..."
                )
                viewModel.isInputValid(
                    user = User(
                        username = usernameState,
                        password = passwordState
                    )
                )
            }
            is RequestState.Error -> {
                snackbarHostState.showSnackbar(
                    message = "Error, please try again!"
                )
            }
            RequestState.Idle -> {
                return@LaunchedEffect
            }
        }
    }

    LaunchedEffect(key1 = goLoginState) {
        if (goLoginState) {
            navigateToLogin()
        }
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UsernameTextField77(
            value = usernameState,
            isError = !isUsernameValid
        ) {
            viewModel.setUsername(it)
            isUsernameValid = it.isNotEmpty()
        }

        Spacer(modifier = modifier.height(16.dp))

        PasswordTextField77(
            value = passwordState,
            isError = !isPasswordValid
        ) {
            viewModel.setPassword(it)
            isPasswordValid = it.isNotEmpty()
        }

        Spacer(modifier = modifier.height(16.dp))

        Button(
            content = { Text(text = stringResource(id = R.string.register)) },
            onClick = { viewModel.loadNewUser() }
        )
        Spacer(modifier = modifier.height(8.dp))

        ClickableText(text = AnnotatedString("Already have an account?")) {
            viewModel.goLogin()
        }
    }
}