package com.example.librarytrackerapp.ui.components.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreenFooter() {
    Row() {
        val annotatedText = buildAnnotatedString {
            append("By signing in, you agree to our ")

            // Empezamos el estilo de subrayado
            pushStyle(
                SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium
                )
            )
            append("Terms of Service")
            pop() // Quitamos el estilo para volver al normal

            append(" and ")

            pushStyle(
                SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium
                )
            )
            append("Privacy Policy")
            pop()
        }
        Text(
            text = annotatedText,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}