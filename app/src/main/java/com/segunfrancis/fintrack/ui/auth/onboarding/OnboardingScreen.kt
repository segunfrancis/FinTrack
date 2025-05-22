@file:Suppress("DEPRECATION")

package com.segunfrancis.fintrack.ui.auth.onboarding

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OnboardingScreen(onSignInClick:() -> Unit = {}, onRegisterClick:() -> Unit = {}) {
    val pages = listOf(
        OnboardingPage(
            title = "Track Your\nExpenses",
            description = "Get insights into where your money goes and make informed financial decisions.",
            image = painterResource(R.drawable.il_track_your_expenses)
        ),
        OnboardingPage(
            title = "Set Savings\nGoals",
            description = "Whether it’s for a vacation, a new car, or an emergency fund, we help you stay on track.",
            image = painterResource(R.drawable.il_set_saving_goals),
        ),
        OnboardingPage(
            title = "Get Financial\nInsights",
            description = "Access detailed reports and analytics to make better financial choices.",
            image = painterResource(R.drawable.il_get_financial_insights),
        )
    )

    val pagerState = rememberPagerState { pages.size }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    FinTrackTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                PagerIndicator(
                    pageCount = pages.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 12.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { page ->
                    OnboardingPageUi(
                        page = pages[page]
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // "Create Account" Button
                Button(
                    onClick = { showBottomSheet = true },
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Create an account", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Sign in text
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
                                    onSignInClick()
                                }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = { showBottomSheet = false },
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                containerColor = MaterialTheme.colorScheme.surface,
                dragHandle = {}
            ) {
                BottomSheetContent(
                    onAccept = {
                        showBottomSheet = false
                        onRegisterClick()
                    },
                    onClose = { showBottomSheet = false }
                )
            }
        }
    }
}

@Composable
private fun BottomSheetContent(
    onAccept: () -> Unit,
    onClose: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            // Title and close button
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Create an account",
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            val annotatedText = buildAnnotatedString {
                append("By pressing accept below you agree to our ")
                pushStringAnnotation(tag = "TOS", annotation = "https://example.com/terms")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("Terms and Conditions")
                }
                pop()
                append(". You can find out more about how we use your data in our ")
                pushStringAnnotation(tag = "PRIVACY", annotation = "https://example.com/privacy")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("Privacy Policy")
                }
                pop()
            }
            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                onClick = { offset ->
                    annotatedText.getStringAnnotations(tag = "TOS", start = offset, end = offset)
                        .firstOrNull()?.let { uriHandler.openUri(it.item) }
                    annotatedText.getStringAnnotations(
                        tag = "PRIVACY",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let { uriHandler.openUri(it.item) }
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onAccept,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    "Accept and Continue",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun OnboardingPageUi(
    page: OnboardingPage
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(30.dp))
        // Placeholder for image
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            // You can replace this with Image(painter = page.image, ...) for real images
            page.image?.let {
                Image(
                    painter = page.image,
                    contentDescription = page.title,
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(350.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF18191A),
    inactiveColor: Color = Color(0xFFCED0D4),
    indicatorHeight: Dp = 4.dp,
    indicatorSpacing: Dp = 6.dp,
    indicatorCornerRadius: Dp = 2.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(indicatorHeight),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(indicatorHeight)
                    .padding(horizontal = indicatorSpacing / 2)
                    .background(
                        color = if (currentPage == index) activeColor else inactiveColor,
                        shape = RoundedCornerShape(indicatorCornerRadius)
                    )
            )
        }
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val image: Painter?
)
