package com.example.composemed.presention.ui.medicine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.composemed.domain.model.models.Medication


@Composable
fun MedicinePage(
    navController: NavHostController,
    viewModel: MedicineViewModel = hiltViewModel()
) {
    when (val medicineState = viewModel.medicineState.value) {
        is UIState.Success -> MedicineListSection(medicines = medicineState.data , viewModel= viewModel)
        is UIState.Loading -> CentralizedProgressIndicator()
        is UIState.Error -> CentralizedErrorView(
            error = medicineState.error,
            onRetry = viewModel::fetchMedicines
        )

        UIState.Empty -> CentralizedTextView(text = "No medication data available!")
    }
}

@Composable
fun MedicineListSection(medicines: List<Medication>, viewModel: MedicineViewModel) {
    LazyColumn {
        items(medicines) { medication ->
            MedicationItem(
                medication = medication,
                onDetailsClick = {},
                onSaveClick = { viewModel.saveMedicine(medication) }
            )
        }
    }
}


@Composable
fun MedicationItem(
    medication: Medication,
    onSaveClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onDetailsClick),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medication.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Dose: ${medication.dose}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Strength: ${medication.strength}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            IconButton(onClick = onSaveClick) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Save")
            }
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



