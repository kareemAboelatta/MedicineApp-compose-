package com.example.composemed.presention.ui.main_pages

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
import androidx.navigation.compose.rememberNavController
import com.example.composemed.R
import com.example.composemed.domain.model.models.Medication
import com.example.composemed.presention.ui.main_pages.favorite_page.FavoritesPage
import com.example.composemed.presention.ui.main_pages.medicine.MedicinePage
import com.example.composemed.presention.ui.main_pages.medicine_details_page.MedicineDetailsPage


enum class Screen(val route: String, @DrawableRes val icon : Int) {
    Home("home", R.drawable.ic_home),
    Favorites("favorites", R.drawable.ic_favorite)
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Home.route)
        {
            composable(Screen.Home.route) {
                MedicinePage(navController = navController)
            }
            composable(Screen.Favorites.route) {
                FavoritesPage(navController = navController)
            }

            composable("MedicineDetailsPage") {
                val result = navController.previousBackStackEntry?.savedStateHandle?.get<Medication>("medicine")
                result?.let {
                    MedicineDetailsPage(navController = navController, medication = result)
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