package com.example.composemed.home.ui.pages.medicine_details_page

import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.ui.utils.PaddingDimensions
import com.example.composemed.home.domain.model.Medication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsPage(
    medication: Medication,
    onBackClick: () -> Unit,

    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                title = {
                    Text(
                        "Medication Details",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MedicineDetailCard("Name", medication.name)
            AnimatedMedicineDetailCard("Description", medication.description)
            MedicineDetailCard("Dose", medication.dose)
            MedicineDetailCard("Strength", medication.strength)
            MedicineDetailCard("Scientific Name", medication.scientificName)
            MedicineDetailCard("Publisher", medication.publisher)

            Spacer(modifier = Modifier.height(PaddingDimensions.large))


        }
    }
}

@Composable
fun AnimatedMedicineDetailCard(label: String, value: String) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .padding(PaddingDimensions.medium)
            .clickable {
                isExpanded = !isExpanded
            },
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(PaddingDimensions.large)) {
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = label,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                )

                IconButton(
                    onClick = {
                        isExpanded = !isExpanded
                    }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "expand"
                    )
                }
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(100))
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@Composable
fun MedicineDetailCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                PaddingDimensions.medium
            ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(PaddingDimensions.small)) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

        }
    }
}