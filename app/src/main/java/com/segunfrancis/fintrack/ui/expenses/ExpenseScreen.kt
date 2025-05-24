package com.segunfrancis.fintrack.ui.expenses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.home.AccountBalanceCard
import com.segunfrancis.fintrack.ui.home.ActivityItem
import com.segunfrancis.fintrack.ui.home.ActivityRow
import com.segunfrancis.fintrack.ui.home.dummyMenus
import com.segunfrancis.fintrack.ui.theme.Orange
import com.segunfrancis.fintrack.ui.theme.TotalExpensesColor
import com.segunfrancis.fintrack.ui.theme.White

@Composable
@Preview
fun ExpensesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Row(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "My Expenses",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {}
                )
            }
            AccountBalanceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                item = dummyMenus().last()
            )
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .clickable { }
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sort your expenses",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 8.dp)
                        .weight(1F)
                )
                Image(
                    painter = painterResource(R.drawable.ic_right_arrow_frame),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Transactions",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Spacer(Modifier.weight(1f))
                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { }
                                .background(color = TotalExpensesColor, shape = CircleShape)
                                .padding(horizontal = 18.dp, vertical = 4.dp),
                        ) {
                            Text(
                                "View All",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 260.dp),
                        contentPadding = PaddingValues(bottom = 9.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(dummyTransactions()) { activity ->
                            ActivityRow(activity)
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            containerColor = Orange,
            contentColor = White,
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

fun dummyTransactions() = listOf(
    ActivityItem(
        iconText = "🪙",
        description = "Food and drinks",
        subDescription = null,
        amount = "₦200,000.00"
    ),
    ActivityItem(
        iconText = "J",
        description = "Transportation",
        subDescription = null,
        amount = "₦10,000.00"
    ),
    ActivityItem(
        iconText = "S",
        description = "Transportation",
        subDescription = null,
        amount = "₦150,000.00"
    ),
    ActivityItem(
        iconText = "R",
        description = "Transportation",
        subDescription = null,
        amount = "₦500.00"
    ),
    ActivityItem(
        iconText = "💡",
        description = "Electricity Bill",
        subDescription = null,
        amount = "₦7,800.00"
    )
)
