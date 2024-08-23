package com.clutchit.loginapp77.ui.screens.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.clutchit.loginapp77.R

@Composable
fun PasswordTextField77(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }
    val visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        maxLines = 1,
        visualTransformation = visualTransformation,
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(id = R.drawable.show)
            else painterResource(id = R.drawable.hide)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }){
                Icon(
                    painter  = image,
                    contentDescription = description
                )
            }
        },
        placeholder = { Text(text = stringResource(id = R.string.password)) },
    )
}