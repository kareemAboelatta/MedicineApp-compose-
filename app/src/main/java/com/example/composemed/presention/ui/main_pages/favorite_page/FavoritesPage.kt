package com.example.composemed.presention.ui.main_pages.favorite_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.common.utils.UIState
import com.example.composemed.domain.model.models.Medication
import com.example.composemed.presention.ui.main_pages.medicine.CentralizedErrorView
import com.example.composemed.presention.ui.main_pages.medicine.CentralizedProgressIndicator
import com.example.composemed.presention.ui.main_pages.medicine.CentralizedTextView

@Composable
fun FavoritesPage(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    when (val savedMedicineState = viewModel.savedMedicineState.value) {
        is UIState.Success -> FavoritesGridSection(
            medicines = savedMedicineState.data,
            navController = navController
        )

        is UIState.Loading -> CentralizedProgressIndicator()
        is UIState.Error -> CentralizedErrorView(
            error = savedMedicineState.error,
            onRetry = viewModel::fetchSavedMedications
        )

        UIState.Empty -> CentralizedTextView(text = "No saved medications available!")
    }
}

@Composable
fun FavoritesGridSection(
    medicines: List<Medication>,
    navController: NavHostController,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Adjust the number of columns
        contentPadding = PaddingValues(all = 8.dp),
        content = {
            items(medicines) { medication ->
                MedicationGridItem(
                    medication = medication,
                    onDetailsClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "medicine",
                            value = medication
                        )
                        navController.navigate("MedicineDetailsPage")
                    },
                )
            }
        }
    )
}

@Composable
fun MedicationGridItem(
    medication: Medication,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onDetailsClick),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = medication.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(text = medication.strength)
            Text(text = medication.dose)


        }
    }
}

