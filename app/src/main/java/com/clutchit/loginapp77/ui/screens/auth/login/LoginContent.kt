package com.clutchit.loginapp77.ui.screens.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import com.clutchit.loginapp77.ui.viewmodel.LoginViewModel
import com.clutchit.loginapp77.util.RequestState
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    viewModel: LoginViewModel = koinViewModel(),
    navigateToWelcome: (String?) -> Unit,
    navigateToRegister: () -> Unit,
    snackbarHostState: SnackbarHostState // TODO Use for errors
) {
    val modifier = Modifier

    val userLoginState by viewModel.userLoginState.collectAsState()
    val goRegisterState by viewModel.goRegister.collectAsState()
    val usernameState by viewModel.username.collectAsState()
    val passwordState by viewModel.password.collectAsState()
    val usernameListState by viewModel.usernameListState.collectAsState()

    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    LaunchedEffect(key1 = userLoginState) {
        when (userLoginState) {
            is RequestState.Success -> {
                snackbarHostState.showSnackbar(
                    message = "Login Success!"
                )
                val tokenState = userLoginState as RequestState.Success
                navigateToWelcome(tokenState.data)
            }
            is RequestState.Loading -> {
                snackbarHostState.showSnackbar(
                    message = "Logging in..."
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
                    message = "Login Error!"
                )
            }
            RequestState.Idle -> {
                return@LaunchedEffect
            }
        }
    }

    LaunchedEffect(key1 = goRegisterState) {
        if (goRegisterState) {
            navigateToRegister()
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
            isError = !isUsernameValid,
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
            content = { Text(text = stringResource(id = R.string.login)) },
            onClick = { viewModel.loadUser() }
        )
        Spacer(modifier = modifier.height(8.dp))

        ClickableText(text = AnnotatedString("Don't have an account?")) {
            viewModel.goRegister()
        }

        Spacer(modifier = modifier.height(16.dp))

        when (usernameListState) {
            is RequestState.Success -> {
                val usernameList = (usernameListState as RequestState.Success).data
                val usernames: List<String> = usernameList.orEmpty().reversed()

                LazyColumn(
                    modifier = modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) {
                    items(usernames.size) {
                        Text(
                            text = "Hi, ${usernames[it]}",
                            modifier = modifier.padding(16.dp)
                        )

                    }
                }
            }
            is RequestState.Loading -> {
                CircularProgressIndicator()
            }
            is RequestState.Error -> {
                Text(text = "Loading Error API Too slow")
            }
            RequestState.Idle -> {
                Text(text = "idle...")
            }
        }
    }
}

