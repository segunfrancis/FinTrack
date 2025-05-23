package com.segunfrancis.fintrack.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.ui.components.CustomInputField
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme

@Composable
fun VerifyAccountScreen(
    userEmail: String = "janedoe@gmail.com",
    onContinue: () -> Unit = {},
    onResend: () -> Unit = {},
    resendSeconds: Int = 50
) {
    var code by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Check your email!",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We have sent an email to $userEmail. Please remember to check your inbox as well as the spam folder.\n\nPlease enter the verification code below to continue with your account.",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            )
            Spacer(modifier = Modifier.height(28.dp))
            CustomInputField(
                value = code,
                onValueChange = { code = it },
                label = "Enter verification code",
                placeholder = "Enter code here"
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onContinue,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    "Continue",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Didn't receive the email?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                val canResend = resendSeconds == 0
                Text(
                    text = if (canResend) "Resend code" else "Resend code in ${resendSeconds}s",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (canResend) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.7f
                        ),
                        textDecoration = if (canResend) TextDecoration.Underline else null,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .clickable(enabled = canResend) { if (canResend) onResend() }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun VerifyAccountPreview() {
    FinTrackTheme {
        VerifyAccountScreen()
    }
}