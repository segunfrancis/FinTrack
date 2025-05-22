package com.segunfrancis.fintrack.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestinations {

    @Serializable
    data object HomeDestination : AppDestinations

    @Serializable
    data object BudgetDestination : AppDestinations

    @Serializable
    data object SavingsDestination : AppDestinations

    @Serializable
    data object ExpensesDestination : AppDestinations

    @Serializable
    data object AccountDestination : AppDestinations
}

sealed interface AuthDestinations {

    @Serializable
    data object OnboardingDestination : AuthDestinations

    @Serializable
    data object LoginDestination : AuthDestinations

    @Serializable
    data object RegisterDestination : AuthDestinations

    @Serializable
    data object VerifyDestination : AuthDestinations

    @Serializable
    data object PasscodeDestination : AuthDestinations
}
