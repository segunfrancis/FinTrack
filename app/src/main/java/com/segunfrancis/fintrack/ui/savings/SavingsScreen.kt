package com.segunfrancis.fintrack.ui.savings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.theme.Orange

@Composable
@Preview
fun SavingsScreen(onCreateSavingsClick: () -> Unit = {}) {

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "My Savings",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .weight(1F)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.il_piggy_bank),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
                Image(
                    painter = painterResource(R.drawable.il_piggy_bank_2),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(
                        Alignment.Center
                    )
                ) {
                    Text(
                        text = "Nothing to see here yet.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Hi there, create a savings\ngoal to get started.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = onCreateSavingsClick,
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Orange),
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Create a savings goal",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 17.sp)
                        )
                    }
                }
            }
        }
    }
}
