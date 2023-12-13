package com.example.composemed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.common.ui.theme.ComposeMedTheme
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


