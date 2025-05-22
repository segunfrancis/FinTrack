package com.segunfrancis.fintrack.ui.auth.passcode

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme

@Composable
fun PasscodeScreen(
    userName: String = "Jane Doe",
    onSignOut: () -> Unit = {},
    onPasscodeComplete: (String) -> Unit = {},
    onContinue: () -> Unit = {}
) {
    var passcode by remember { mutableStateOf("") }
    val passcodeLength = 4
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 22.sp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = userName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(52.dp))
            PasscodeDots(passcode = passcode, passcodeLength = passcodeLength)
            Spacer(modifier = Modifier.height(32.dp))
            PasscodeKeyboard(
                onNumberClick = { digit ->
                    if (passcode.length < passcodeLength) {
                        passcode += digit
                        if (passcode.length == passcodeLength) {
                            onPasscodeComplete(passcode)
                        }
                    }
                },
                onBackspace = {
                    if (passcode.isNotEmpty()) {
                        passcode = passcode.dropLast(1)
                    }
                },
                onSignOut = onSignOut
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Button(
                onClick = onContinue,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = passcode.length == passcodeLength,
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
        }
    }
}

@Composable
fun PasscodeDots(passcode: String, passcodeLength: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(passcodeLength) { index ->
            val filled = index < passcode.length
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(width = 56.dp, height = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (filled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                )
            }
        }
    }
}

@Composable
fun PasscodeKeyboard(
    onNumberClick: (String) -> Unit,
    onBackspace: () -> Unit,
    onSignOut: () -> Unit
) {
    val keys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "⌫")
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        keys.forEachIndexed { index, row ->
            val isLastColumn = keys.size - 1 == index
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = if (isLastColumn) Color.Unspecified else MaterialTheme.colorScheme.outline)
            ) {
                row.forEach { key ->
                    when (key) {
                        "" -> Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.height(64.dp)
                        ) {
                            Text(
                                text = "Sign out",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .clickable { onSignOut() }
                            )
                        }

                        "⌫" -> IconButton(
                            onClick = onBackspace,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_delete),
                                contentDescription = "Backspace",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        else -> Button(
                            onClick = { onNumberClick(key) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isLastColumn) MaterialTheme.colorScheme.outline else Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier
                                .size(64.dp)
                                .background(color = if (isLastColumn) MaterialTheme.colorScheme.outline else Color.Transparent)
                        ) {
                            Text(
                                text = key,
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
@Preview
fun PasscodePreview() {
    FinTrackTheme {
        PasscodeScreen()
    }
}
