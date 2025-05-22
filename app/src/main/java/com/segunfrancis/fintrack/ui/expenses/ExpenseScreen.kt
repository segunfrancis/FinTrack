package com.segunfrancis.fintrack.ui.expenses

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpensesScreen() {
    Text(text = "Expense Screen", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(24.dp))
}
