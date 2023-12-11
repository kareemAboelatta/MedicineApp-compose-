package com.example.composemed.presention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemed.presention.theme.ComposeMedTheme
import com.example.composemed.presention.ui.medicine.MedicinePage
import com.example.composemed.presention.ui.medicine.MedicineViewModel
import dagger.hilt.android.AndroidEntryPoint


/// MedicationManagementAndUserGreetingSystem
///

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMedTheme {
                val navController = rememberNavController()
                val medicineViewModel: MedicineViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "MedicinePage") {
                    composable("MedicinePage") {
                        MedicinePage(navController = navController, viewModel = medicineViewModel)
                    }



                }
            }
        }
    }
}
