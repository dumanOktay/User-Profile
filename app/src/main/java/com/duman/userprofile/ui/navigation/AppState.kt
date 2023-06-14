package com.duman.userprofile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class AppState(val navHostController: NavHostController)

@Composable
fun rememberAppState(): AppState {
    return AppState(navHostController = rememberNavController())
}
