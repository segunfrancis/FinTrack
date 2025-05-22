@file:Suppress("DEPRECATION")

package com.segunfrancis.fintrack.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.ui.components.CustomInputField
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme

@Composable
fun CreateAccountScreen(
    onCreateAccount: () -> Unit = {},
    onSignIn: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var referral by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 0.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Let’s get started! 🎉",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Join us and start managing your finances with Fintrack today.",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            )
            Spacer(modifier = Modifier.height(32.dp))
            CustomInputField(
                value = name,
                onValueChange = { name = it },
                label = "First & Last Name",
                placeholder = "e.g John Doe",
            )
            Spacer(modifier = Modifier.height(18.dp))
            CustomInputField(
                value = email,
                onValueChange = { email = it },
                label = "Email address",
                placeholder = "e.g email@mail.com",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(18.dp))
            CustomInputField(
                value = referral,
                onValueChange = { referral = it },
                label = "Enter a referral code(optional)",
                placeholder = "e.g JFH489THF",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onCreateAccount,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    "Create an account",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val annotatedText = buildAnnotatedString {
                    append("Already have an account? ")
                    pushStringAnnotation(
                        tag = "SIGN_IN",
                        annotation = "sign_in"
                    )
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Sign in")
                    }
                    pop()
                }
                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations("SIGN_IN", offset, offset)
                            .firstOrNull()?.let {
                                // TODO: Handle sign in navigation
                            }
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun CreateAccountScreenPreview() {
    FinTrackTheme {
        CreateAccountScreen()
    }
}
