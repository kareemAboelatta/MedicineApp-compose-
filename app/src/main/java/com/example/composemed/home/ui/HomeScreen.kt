package com.example.composemed.home.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.composemed.home.domain.model.models.Medication
import com.example.composemed.home.ui.pages.favorite_page.FavoritesPage
import com.example.composemed.home.ui.pages.medicine.MedicinePage
import com.example.composemed.home.ui.pages.medicine_details_page.MedicineDetailsPage



enum class Screen(val route: String, @DrawableRes val icon: Int) {
    Home("medicine", R.drawable.ic_home),
    Favorites("favorites", R.drawable.ic_favorite)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(logout: () -> Unit) {
    val homeNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    IconButton(
                        onClick = logout
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_logout),
                            contentDescription = "logout"
                        )
                    }
                }
            )
        },
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