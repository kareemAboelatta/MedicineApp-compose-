package com.example.composemed

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composemed.auth.ui.login.LoginScreen
import com.example.composemed.home.ui.HomeScreen



@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    )
    {


        //Auth
        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable("login") {
                LoginScreen(navController = navController)

            }
            composable("register") {

            }

        }


        //Home
        composable("home") {
            HomeScreen(
                //when this fun invoked anywhere in HomeScreen it, it will leave home pages
                logout = {
                    navController.navigate("auth") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            )

        }


    }

}



