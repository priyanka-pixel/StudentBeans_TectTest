package com.priyanka.studentbeans_tecttest.authorisation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen.SignInScreen
import com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen.SignInViewModel
import com.priyanka.studentbeans_tecttest.authorisation.presentation.signup_screen.SignUpScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController(),
               loginViewModel: SignInViewModel
){
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(route = Screen.SignIn.route) {
           SignInScreen(onNavToHomePage = { navController.navigate(Screen.MainScreen.route) {
               launchSingleTop = true
               popUpTo(route = Screen.SignIn.route) {
                   inclusive = true
               }
               }
           }) {
               navController.navigate(Screen.SignUp.route){
                   launchSingleTop = true
                   popUpTo(Screen.SignIn.route){
                       inclusive = true
                   }
               }
           }

           }
        composable(route = Screen.SignUp.route){
            SignUpScreen(onNavToHomePage = {
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(Screen.SignUp.route) {
                        inclusive = true
                    }
                }

            }){
                navController.navigate(Screen.SignIn.route){

                }
            }
        }
        composable(route = Screen.MainScreen.route){
            Screen.SignIn
        }
        }

}