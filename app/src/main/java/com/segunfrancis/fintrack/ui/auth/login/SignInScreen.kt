@file:Suppress("DEPRECATION")

package com.segunfrancis.fintrack.ui.auth.login

import android.util.Patterns
import androidx.compose.foundation.background
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
fun SignInScreen(onSignIn: () -> Unit = {}, onSignUpClick: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }

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
                text = "Welcome back 🎉",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sign in to your account and start managing your finances with Fintrack today.",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            )
            Spacer(modifier = Modifier.height(28.dp))
            CustomInputField(
                value = email,
                onValueChange = { email = it },
                label = "Email address",
                placeholder = "e.g. email@mail.com",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onSignIn,
                shape = RoundedCornerShape(30.dp),
                enabled = email.matches(Patterns.EMAIL_ADDRESS.toRegex()),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    "Sign In",
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
                    append("Do not have an account? ")
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
                        append("Sign Up")
                    }
                    pop()
                }
                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations("SIGN_UP", offset, offset)
                            .firstOrNull()?.let {
                                onSignUpClick()
                            }
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun SignInPreview() {
    FinTrackTheme {
        SignInScreen()
    }
}