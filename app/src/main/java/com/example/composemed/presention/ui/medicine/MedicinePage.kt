package com.example.composemed.presention.ui.medicine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.common.utils.CustomError
import com.example.common.utils.UIState
import com.example.common.utils.getDisplayMessage
import com.example.composemed.domain.model.Medication


@Composable
fun MedicinePage(
    navController: NavHostController,
    viewModel: MedicineViewModel = hiltViewModel()
) {
    when (val medicineState = viewModel.medicineState.value) {
        is UIState.Success -> MedicineListSection(medicines = medicineState.data)
        is UIState.Loading -> CentralizedProgressIndicator()
        is UIState.Error -> CentralizedErrorView(error = medicineState.error, onRetry = viewModel::fetchUsers)
        UIState.Empty -> CentralizedTextView(text = "No medication data available!")
    }
}

@Composable
fun MedicineListSection(medicines: List<Medication>) {
    LazyColumn {
        items(medicines) { medication ->
            MedicationItem(medication = medication)
        }
    }
}


@Composable
fun MedicationItem(medication: Medication) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = medication.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Dose: ${medication.dose}")
            Text(text = "Strength: ${medication.strength}")
            Text(text = "Description: ${medication.description}")
            Text(text = "Scientific Name: ${medication.scientificName}")
        }
    }
}
















@Composable
fun CentralizedProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CentralizedErrorView(error: CustomError, onRetry: () -> Unit = {}) {
    val errorMessage = error.getDisplayMessage() // Assuming you added an extension on CustomError
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun CentralizedTextView(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}



