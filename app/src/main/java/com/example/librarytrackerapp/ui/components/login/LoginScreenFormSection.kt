package com.example.librarytrackerapp.ui.components.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreenFormSection() {
    var isLogin by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color(0xFF232F48), RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        LoginScreenSegmentItem(
            label = "Sign In",
            isSelected = isLogin,
            modifier = Modifier.weight(1f),
            onClick = {
                isLogin = true
            }
        )
        LoginScreenSegmentItem(
            label = "Create Account",
            isSelected = !isLogin,
            modifier = Modifier.weight(1f),
            onClick = {
                isLogin = false
            }
        )
    }
    AnimatedContent(
        targetState = isLogin,
        label = "form_transition"
    ) { targetIsLogin ->
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            if (targetIsLogin) {
                LoginScreenLoginEmail()
                LoginScreenLoginPassword()
            } else {
                LoginScreenLoginEmail()
                LoginScreenLoginPassword()
                LoginScreenLoginPassword()
            }

            Spacer(Modifier.height(8.dp))
            LoginScreenButtonLogin(
                text = if (targetIsLogin) "Sign In" else "Create Account"
            )
        }
    }
}