package com.example.librarytrackerapp.ui.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.R

@Composable
fun LoginScreenSocials() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color(0xFF324467),
            thickness = 1.dp
        )
        Text(
            text = "OR CONTINUE WITH",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color(0xFF324467),
            thickness = 1.dp
        )
    }
    Spacer(Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LoginScreenSocialButton(
            text = "Google",
            iconRes = R.drawable.google,
            onClick = { /* Lógica de Google */ },
            modifier = Modifier.weight(1f)
        )

        LoginScreenSocialButton(
            text = "Apple",
            iconRes = R.drawable.ios,
            onClick = { /* Lógica de Apple */ },
            modifier = Modifier.weight(1f)
        )
    }
}