package com.example.composemed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composemed.auth.ui.login.LoginScreen
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.ui.HomeScreen
import com.example.composemed.home.ui.pages.medicine_details_page.MedicineDetailsPage

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    // Add other screens here
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {


        //Auth
        navigation(
            startDestination = Screen.Login.route,
            route = Screen.Auth.route
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginClicked = { username ->
                        navController.navigate(Screen.Home.route + "/${username}") {
                            popUpTo(Screen.Auth.route) { inclusive = true }
                        }
                    },
                )

            }
            composable(Screen.Register.route) {}

        }


        //Home
        composable(Screen.Home.route + "/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Default name"

            HomeScreen(
                username = username,
                //when this fun invoked anywhere in HomeScreen it, it will leave home pages
                logout = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )

        }


    }


}





