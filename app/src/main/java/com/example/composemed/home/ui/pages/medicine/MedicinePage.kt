package com.example.composemed.home.ui.pages.medicine

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.ui.utils.PaddingDimensions
import com.example.common.utils.UIState
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.ui.common.CentralizedErrorView
import com.example.composemed.home.ui.common.CentralizedProgressIndicator
import com.example.composemed.home.ui.common.CentralizedTextView
import kotlinx.coroutines.launch


@Composable
fun MedicinePage(
    onMedicationClick: (Medication) -> Unit,
    viewModel: MedicineViewModel = hiltViewModel()
) {
    when (val medicineState = viewModel.medicineState.collectAsState().value) {
        is UIState.Success -> MedicineListSection(
            medicines = medicineState.data,
            viewModel = viewModel,
            onMedicationClick = onMedicationClick
        )

        is UIState.Loading -> CentralizedProgressIndicator()
        is UIState.Error -> CentralizedErrorView(
            error = medicineState.error,
            onRetry = viewModel::fetchMedicines
        )

        UIState.Empty -> CentralizedTextView(text = "No medication data available!")
    }
}

@Composable
fun MedicineListSection(
    medicines: List<Medication>,
    viewModel: MedicineViewModel,
    onMedicationClick: (Medication) -> Unit,
) {
    Box {
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        LazyColumn(
            state = listState
        ) {
            items(medicines) { medication ->
                MedicationItem(
                    medication = medication,
                    onDetailsClick = { onMedicationClick(medication) },
                    onSaveClick = { viewModel.saveMedicine(medication) }
                )
            }
        }

        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            })
        }


    }
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = PaddingDimensions.xxLarge), Alignment.BottomCenter
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp)),

            ) {
            Icon(Icons.Filled.KeyboardArrowUp, "arrow up")
            Text(
                text = "Scroll To Top",
                style = MaterialTheme.typography.labelMedium
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
    var isLiked by remember { mutableStateOf(false) } //dummy
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
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    onSaveClick()
                }) {
                Icon(
                    imageVector = if (isLiked) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like"
                )
            }
        }
    }
}




