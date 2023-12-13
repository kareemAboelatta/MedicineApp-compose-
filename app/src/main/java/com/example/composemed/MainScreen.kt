package com.example.composemed

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composemed.auth.ui.login.LoginScreen
import com.example.composemed.home.domain.model.models.Medication
import com.example.composemed.home.ui.main_pages.favorite_page.FavoritesPage
import com.example.composemed.home.ui.main_pages.medicine.MedicinePage
import com.example.composemed.home.ui.main_pages.medicine_details_page.MedicineDetailsPage


enum class Screen(val route: String, @DrawableRes val icon: Int) {
    Home("medicine", R.drawable.ic_home),
    Favorites("favorites", R.drawable.ic_favorite)
}


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
            val homeNavController = rememberNavController()

            Scaffold(
                bottomBar = { BottomNavigationBar(navController = homeNavController) }
            ) { scaffoldPadding ->
                NavHost(
                    modifier = Modifier.padding(scaffoldPadding),
                    navController = homeNavController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        MedicinePage(navController = homeNavController)
                    }
                    composable(Screen.Favorites.route) {
                        FavoritesPage(navController = homeNavController)
                    }


                    composable("MedicineDetailsPage") {
                        val result =
                            homeNavController.previousBackStackEntry?.savedStateHandle?.get<Medication>(
                                "medicine"
                            )
                        result?.let {
                            MedicineDetailsPage(
                                navController = homeNavController,
                                medication = result
                            )
                        }
                    }
                }
            }
        }



    }

}


@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar {
        val currentRoute = currentRoute(navController)
        Screen.values().forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        null,
                    )
                }, // Set your icons here
                label = { Text(screen.name) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}