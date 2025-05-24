package com.segunfrancis.fintrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.segunfrancis.fintrack.navigation.AppBottomNavigationBar
import com.segunfrancis.fintrack.navigation.AppDestinations
import com.segunfrancis.fintrack.navigation.AuthDestinations
import com.segunfrancis.fintrack.ui.account.AccountScreen
import com.segunfrancis.fintrack.ui.auth.login.SignInScreen
import com.segunfrancis.fintrack.ui.auth.onboarding.OnboardingScreen
import com.segunfrancis.fintrack.ui.auth.passcode.PasscodeScreen
import com.segunfrancis.fintrack.ui.auth.register.CreateAccountScreen
import com.segunfrancis.fintrack.ui.auth.register.VerifyAccountScreen
import com.segunfrancis.fintrack.ui.budget.BudgetScreen
import com.segunfrancis.fintrack.ui.budget.CreateBudgetScreen
import com.segunfrancis.fintrack.ui.expenses.ExpensesScreen
import com.segunfrancis.fintrack.ui.home.HomeScreen
import com.segunfrancis.fintrack.ui.savings.CreateSavingsScreen
import com.segunfrancis.fintrack.ui.savings.SavingsScreen
import com.segunfrancis.fintrack.ui.theme.FinTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            FinTrackTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentBackStack?.destination?.hierarchy?.any {
                                it.hasRoute(AppDestinations.HomeDestination::class) || it.hasRoute(
                                    AppDestinations.BudgetDestination::class
                                ) || it.hasRoute(
                                    AppDestinations.SavingsDestination::class
                                ) || it.hasRoute(
                                    AppDestinations.ExpensesDestination::class
                                ) || it.hasRoute(
                                    AppDestinations.AccountDestination::class
                                )
                            } == true) {
                            AppBottomNavigationBar(navController = navController)
                        }
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AuthDestinations.OnboardingDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<AuthDestinations.OnboardingDestination> {
                            OnboardingScreen(
                                onSignInClick = { navController.navigate(AuthDestinations.LoginDestination) },
                                onRegisterClick = { navController.navigate(AuthDestinations.RegisterDestination) }
                            )
                        }
                        composable<AuthDestinations.LoginDestination> {
                            SignInScreen(
                                onSignIn = {
                                    navController.navigate(AuthDestinations.VerifyDestination)
                                },
                                onSignUpClick = {
                                    navController.navigate(AuthDestinations.RegisterDestination) {
                                        popUpTo(AuthDestinations.VerifyDestination) {
                                            inclusive = true
                                        }
                                    }
                                })
                        }
                        composable<AuthDestinations.RegisterDestination> {
                            CreateAccountScreen(
                                onCreateAccount = { navController.navigate(AuthDestinations.VerifyDestination) },
                                onSignIn = {
                                    navController.navigate(AuthDestinations.LoginDestination) {
                                        popUpTo(AuthDestinations.RegisterDestination)
                                    }
                                }
                            )
                        }
                        composable<AuthDestinations.VerifyDestination> {
                            VerifyAccountScreen(onContinue = {
                                navController.navigate(
                                    AuthDestinations.PasscodeDestination
                                )
                            })
                        }
                        composable<AuthDestinations.PasscodeDestination> {
                            PasscodeScreen(onContinue = { navController.navigate(AppDestinations.HomeDestination) })
                        }
                        composable<AppDestinations.HomeDestination> {
                            HomeScreen()
                        }
                        composable<AppDestinations.BudgetDestination> {
                            BudgetScreen(onCreateBudgetClick = {
                                navController.navigate(AppDestinations.CreateBudgetDestination)
                            })
                        }
                        composable<AppDestinations.CreateBudgetDestination> {
                            CreateBudgetScreen(onBack = { navController.navigateUp() })
                        }
                        composable<AppDestinations.ExpensesDestination> {
                            ExpensesScreen()
                        }
                        composable<AppDestinations.SavingsDestination> {
                            SavingsScreen(onCreateSavingsClick = {
                                navController.navigate(AppDestinations.CreateSavingsDestination)
                            })
                        }
                        composable<AppDestinations.CreateSavingsDestination> {
                            CreateSavingsScreen(onBack = { navController.navigateUp() })
                        }
                        composable<AppDestinations.AccountDestination> {
                            AccountScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinTrackTheme {
        Greeting("Android")
    }
}
