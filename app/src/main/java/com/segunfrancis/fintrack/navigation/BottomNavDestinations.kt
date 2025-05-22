package com.segunfrancis.fintrack.navigation

import com.segunfrancis.fintrack.R

sealed class BottomNavDestination(
    open val route: AppDestinations,
    val label: String,
    val iconRes: Int
) {
    data object Home : BottomNavDestination(
        route = AppDestinations.HomeDestination,
        label = "Home",
        iconRes = R.drawable.ic_home
    )

    data object Budgets : BottomNavDestination(
        route = AppDestinations.BudgetDestination,
        label = "Budgets",
        iconRes = R.drawable.ic_budgets
    )

    data object Savings : BottomNavDestination(
        route = AppDestinations.SavingsDestination,
        label = "Savings",
        iconRes = R.drawable.ic_savings
    )

    data object Expenses : BottomNavDestination(
        route = AppDestinations.ExpensesDestination,
        label = "Expenses",
        iconRes = R.drawable.ic_expenses
    )

    data object Account : BottomNavDestination(
        route = AppDestinations.AccountDestination,
        label = "Account",
        iconRes = R.drawable.ic_account
    )
}

val bottomNavDestinations = listOf(
    BottomNavDestination.Home,
    BottomNavDestination.Budgets,
    BottomNavDestination.Savings,
    BottomNavDestination.Expenses,
    BottomNavDestination.Account
)