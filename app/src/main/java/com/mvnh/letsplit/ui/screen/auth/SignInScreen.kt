package com.mvnh.letsplit.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mvnh.letsplit.R
import com.mvnh.letsplit.ui.viewmodel.AuthViewModel
import com.mvnh.letsplit.ui.viewmodel.state.AuthState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    viewModel: AuthViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val authState by viewModel.authState.collectAsState()
    val isLoading = authState is AuthState.Loading
    val isError = authState is AuthState.Error

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { if (it.length <= 12) phoneNumber = it },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                label = { Text(text = stringResource(id = R.string.phone_number)) },
                isError = isError,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                label = { Text(text = stringResource(id = R.string.password)) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val icon =
                            if (passwordVisibility) R.drawable.visibility_off_24px else R.drawable.visibility_24px
                        val description =
                            if (passwordVisibility) stringResource(id = R.string.hide_password) else stringResource(
                                id = R.string.show_password
                            )

                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = description
                        )
                    }
                },
                supportingText = {
                    if (isError) {
                        Text(text = (authState as AuthState.Error).message)
                    }
                },
                isError = isError,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        coroutineScope.launch {
                            viewModel.signIn(phoneNumber, password)
                        }
                    }
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.signIn(phoneNumber, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                enabled = !isLoading
            ) {
                Text(text = stringResource(id = R.string.sign_in))
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}