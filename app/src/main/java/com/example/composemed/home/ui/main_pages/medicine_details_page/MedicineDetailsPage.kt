package com.example.composemed.home.ui.main_pages.medicine_details_page

import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composemed.home.domain.model.models.Medication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsPage(medication: Medication, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Medication Details") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MedicineDetailCard("Name", medication.name)
            MedicineDetailCard("Dose", medication.dose)
            MedicineDetailCard("Strength", medication.strength)
            MedicineDetailCard("Description", medication.description)
            MedicineDetailCard("Scientific Name", medication.scientificName)
            MedicineDetailCard("Publisher", medication.publisher)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigateUp() }) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun MedicineDetailCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, style = MaterialTheme.typography.titleLarge, color = Color.DarkGray)
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Text(text = value, style = MaterialTheme.typography.bodySmall, color = Color.Black)
            }
        }
    }
}