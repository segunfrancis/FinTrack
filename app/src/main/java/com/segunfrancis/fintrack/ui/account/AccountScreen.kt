package com.segunfrancis.fintrack.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.theme.Red
import com.segunfrancis.fintrack.ui.theme.Teal

@Composable
@Preview
fun AccountScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))
            Text(
                text = "My Account",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(20.dp))
            UserProfileCard(
                name = "Aderinsola Adejuwon",
                email = "janedoe@gmail.com",
                phone = "+234 90 1619 2553",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(18.dp))
            ProfileMenuSection(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(24.dp))
            ContactInfoCard(
                email = "contact@fintrack.com",
                phone = "+234 90 161 92553",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "v1.0.0",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun UserProfileCard(
    name: String,
    email: String,
    phone: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Teal)
            .padding(16.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    )
                    Spacer(Modifier.height(1.dp))
                    Text(
                        text = phone,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    )
                }
                Spacer(Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.ic_edit_account),
                    contentDescription = "Edit Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
            }
        }
    }
}

@Composable
fun ProfileMenuSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ProfileMenuItem(
            icon = painterResource(R.drawable.ic_security),
            title = "Security",
            subtitle = "Enable or disable biometrics"
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItem(
            icon = painterResource(R.drawable.ic_insights),
            title = "Insights and Reports",
            subtitle = "A detailed summary of how you have managed your money"
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItem(
            icon = painterResource(R.drawable.ic_support),
            title = "Support",
            subtitle = "We can assist you if you have any queries"
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItem(
            icon = painterResource(R.drawable.ic_feedback),
            title = "Give feedback",
            subtitle = "Help us better the experience for you."
        )
        Spacer(Modifier.height(18.dp))
        ProfileMenuItemNoIcon(
            title = "Log Out",
            titleColor = Red
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItemNoIcon(
            title = "About Fintrack",
            endIcon = painterResource(R.drawable.ic_arrow_right)
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItemNoIcon(
            title = "Privacy & Terms of Service",
            endIcon = painterResource(R.drawable.ic_arrow_right)
        )
        Spacer(Modifier.height(8.dp))
        ProfileMenuItemNoIcon(
            title = "Request account deletion",
            endIcon = painterResource(R.drawable.ic_arrow_right)
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: Painter,
    title: String,
    subtitle: String? = null,
    titleColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val clickableModifier = Modifier
        .fillMaxWidth()
        .heightIn(min = if (!subtitle.isNullOrEmpty()) 54.dp else 44.dp)
        .clip(RoundedCornerShape(8.dp))
        .clickable { }
        .padding(horizontal = 0.dp, vertical = 0.dp)
    Row(
        modifier = clickableModifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(8.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = titleColor
                ),
                fontSize = 16.sp
            )
            Spacer(Modifier.height(4.dp))
            subtitle?.let {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.ic_right_arrow_frame),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )
    }
}

@Composable
fun ProfileMenuItemNoIcon(
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    endIcon: Painter? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = titleColor
            ),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 8.dp)
                .weight(1F)
        )
        endIcon?.let {
            Image(
                painter = endIcon,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun ContactInfoCard(
    email: String,
    phone: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Teal)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Contact info",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
