package com.example.librarytrackerapp.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarytrackerapp.ui.components.login.LoginScreenFooter
import com.example.librarytrackerapp.ui.components.login.LoginScreenFormSection
import com.example.librarytrackerapp.ui.components.login.LoginScreenHero
import com.example.librarytrackerapp.ui.components.login.LoginScreenSocials
import com.example.librarytrackerapp.ui.components.login.LoginScreenTopBar

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { LoginScreenTopBar(navigateToHome = navigateToHome) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginScreenHero()
            Spacer(Modifier.height(32.dp))
            LoginScreenFormSection(loginViewModel = loginViewModel)
            Spacer(Modifier.height(32.dp))
            LoginScreenSocials()
            Spacer(Modifier.height(48.dp))
            LoginScreenFooter()
            Spacer(Modifier.height(16.dp))
        }
    }
}

