package com.priyanka.studentbeans_tecttest.authorisation.navigation

sealed class Screen(val route: String) {
    object SignUp : Screen("signUp_screen")
    object SignIn: Screen("logIn_screen")
    object MainScreen: Screen("Main_screen")
}
