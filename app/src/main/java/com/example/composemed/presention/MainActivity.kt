package com.example.composemed.presention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composemed.presention.theme.ComposeMedTheme
import com.example.composemed.presention.ui.main_page.MainScreen
import dagger.hilt.android.AndroidEntryPoint


/// MedicationManagementAndUserGreetingSystem
///

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMedTheme {
                MainScreen()
            }
        }
    }
}


