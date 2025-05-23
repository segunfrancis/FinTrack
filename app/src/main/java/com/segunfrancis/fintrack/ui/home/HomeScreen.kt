package com.segunfrancis.fintrack.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.theme.Black
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme
import com.segunfrancis.fintrack.ui.theme.MonthlyBudgetColor
import com.segunfrancis.fintrack.ui.theme.Orange
import com.segunfrancis.fintrack.ui.theme.Teal
import com.segunfrancis.fintrack.ui.theme.Teal2
import com.segunfrancis.fintrack.ui.theme.TotalExpensesColor
import com.segunfrancis.fintrack.ui.theme.TotalSavingsColor

@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState { dummyMenus().size }

    val activities = remember { dummyActivities() }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(Modifier.height(12.dp))
                HomeTopBar()
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Your financial journey starts here.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                Spacer(Modifier.height(18.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    pageSpacing = 8.dp
                ) {
                    AccountBalanceCard(
                        modifier = Modifier.fillMaxWidth(),
                        item = dummyMenus()[it]
                    )
                }
                Spacer(Modifier.height(6.dp))
                PagerIndicator(
                    pageCount = dummyMenus().size,
                    currentPage = pagerState.currentPage,
                    activeColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
                Spacer(Modifier.height(20.dp))
                YourWeekCard()
                Spacer(Modifier.height(18.dp))
                RecentActivitiesSection(activities = activities)
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Column(modifier = Modifier.weight(1F), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Hello, Jane",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Image(
            painter = painterResource(R.drawable.ic_menu_account),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentWidth()
                .size(48.dp)
                .clip(CircleShape)
                .clickable {  }
                .padding(4.dp)
        )

        Spacer(Modifier.width(10.dp))

        Image(
            painter = painterResource(R.drawable.ic_menu_notification),
            contentDescription = "Notifications",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentWidth()
                .size(48.dp)
                .clip(CircleShape)
                .clickable {  }
                .padding(4.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AccountBalanceCard(modifier: Modifier = Modifier, item: MenuCardItem = dummyMenus().last()) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(item.background),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = item.textColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                )
                Spacer(Modifier.weight(1F))
                Row(
                    modifier = Modifier
                        .background(
                            color = item.actionBackgroundColor,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .clickable {  }
                        .padding(start = 12.dp, end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.actionTitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = item.actionTextColor,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = item.actionTextColor
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.balance,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = item.textColor,
                        fontSize = 24.sp
                    )
                )
                Spacer(Modifier.width(10.dp))
                Image(
                    painter = item.getPrivacyIcon(),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(7.dp))
            Text(
                text = item.info,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = item.textColor,
                    fontSize = 13.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(24.dp))
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = { item.progress },
                strokeCap = StrokeCap.Round,
                trackColor = item.getProgressIndicatorColor().copy(alpha = 0.4F),
                color = item.getProgressIndicatorColor(),
                drawStopIndicator = {},
                gapSize = 0.dp
            )
            Spacer(Modifier.height(16.dp))
        }
        Image(
            painter = painterResource(item.illustration),
            contentDescription = null,
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color,
    inactiveColor: Color = Color(0xFFCED0D4),
    indicatorHeight: Int = 4,
    indicatorSpacing: Int = 8
) {
    Row(
        modifier = modifier.height(indicatorHeight.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(indicatorHeight.dp)
                    .padding(horizontal = (indicatorSpacing / 2).dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (index == currentPage) activeColor else inactiveColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourWeekCard() {
    FinTrackTheme {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Your Week",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Row {
                        Text(
                            text = "in",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Money",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Orange,
                            )
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "See your past week in money",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    )
                }
                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Teal2, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "See week in money",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun RecentActivitiesSection(activities: List<ActivityItem> = dummyActivities()) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
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
                    text = "Recent Activities",
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
                        .background(color = Teal2, shape = CircleShape)
                        .padding(horizontal = 18.dp, vertical = 4.dp),
                ) {
                    Text(
                        "View All",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
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
                items(activities) { activity ->
                    ActivityRow(activity)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityRow(activity: ActivityItem = dummyActivities().first()) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Teal2,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = activity.description.first().toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Teal,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = activity.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = activity.subDescription,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Normal
                    ),
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                text = activity.amount,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 14.sp
            )
        }
    }
}

data class ActivityItem(
    val displayDate: String,
    val showDate: Boolean,
    val iconText: String,
    val description: String,
    val subDescription: String,
    val amount: String
)

fun dummyActivities(): List<ActivityItem> = listOf(
    ActivityItem(
        displayDate = "Today, 14/07/2024",
        showDate = true,
        iconText = "🪙",
        description = "Created a new budget \"Trip to Nairobi\"",
        subDescription = "a day ago",
        amount = "₦200,000.00"
    ),
    ActivityItem(
        displayDate = "",
        showDate = false,
        iconText = "J",
        description = "John Ogaga",
        subDescription = "Zenith Bank 12:03 AM",
        amount = "₦10,000.00"
    ),
    ActivityItem(
        displayDate = "Yesterday, 13/07/2024",
        showDate = true,
        iconText = "S",
        description = "Salary Credited",
        subDescription = "GTBank 09:00 AM",
        amount = "₦150,000.00"
    ),
    ActivityItem(
        displayDate = "",
        showDate = false,
        iconText = "R",
        description = "Recharge Card",
        subDescription = "10:00 AM",
        amount = "₦500.00"
    ),
    ActivityItem(
        displayDate = "",
        showDate = false,
        iconText = "💡",
        description = "Paid Electricity Bill",
        subDescription = "PHED",
        amount = "₦7,800.00"
    )
)

data class MenuCardItem(
    val title: String,
    val balance: String,
    val actionTitle: String,
    val info: String,
    @DrawableRes val illustration: Int,
    val background: Color,
    val progress: Float = 1F,
    val textColor: Color,
    val actionTextColor: Color,
    val actionBackgroundColor: Color
) {
    @Composable
    fun getProgressIndicatorColor(): Color {
        return if (textColor == Black) {
            Orange
        } else {
            MaterialTheme.colorScheme.onPrimary
        }
    }

    @Composable
    fun getPrivacyIcon(): Painter {
        return if (textColor == Black || background == Teal) {
            painterResource(R.drawable.ic_privacy_teal)
        } else {
            painterResource(R.drawable.ic_privacy_orange)
        }
    }
}

fun dummyMenus(): List<MenuCardItem> {
    return listOf(
        MenuCardItem(
            title = "Account Balance",
            balance = "₦ 1,000,500.55",
            actionTitle = "Manage Accounts",
            info = "The total balance from your linked accounts.",
            illustration = R.drawable.il_manage_accounts,
            background = Teal,
            textColor = MonthlyBudgetColor,
            actionTextColor = Teal,
            actionBackgroundColor = MonthlyBudgetColor
        ),
        MenuCardItem(
            title = "Total Savings",
            balance = "₦ 50,530.00",
            actionTitle = "View Savings",
            info = "You need N250,000 to meet your targets.",
            illustration = R.drawable.il_view_savings,
            background = TotalSavingsColor,
            progress = 0.75F,
            textColor = MonthlyBudgetColor,
            actionTextColor = TotalSavingsColor,
            actionBackgroundColor = MonthlyBudgetColor
        ),
        MenuCardItem(
            title = "Monthly Budget",
            balance = "₦ 1,000,500.55",
            actionTitle = "Manage Budget",
            info = "left out of N200,000,000 budgeted.",
            illustration = R.drawable.il_manage_budget,
            background = MonthlyBudgetColor,
            progress = 0.65F,
            textColor = Black,
            actionTextColor = MonthlyBudgetColor,
            actionBackgroundColor = Teal
        ),
        MenuCardItem(
            title = "Total expenses",
            balance = "₦ 1,000,500.55",
            actionTitle = "View expenses",
            info = "spent in the last 7 days",
            illustration = R.drawable.il_total_expenses,
            background = TotalExpensesColor,
            progress = 0.95F,
            textColor = Black,
            actionTextColor = MonthlyBudgetColor,
            actionBackgroundColor = Teal
        ),
    )
}
