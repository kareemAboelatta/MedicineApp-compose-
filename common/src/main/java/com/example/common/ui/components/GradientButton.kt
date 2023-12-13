package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    gradientColors: List<Color>,
    text: String,
    onClick: ()-> Unit,
    cornerRadius: Dp = 20.dp,
    roundedCornerShape: RoundedCornerShape = CircleShape,
    elevation: Dp = 10.dp,
    withShadow:Boolean = true
) {
    val modifier = if(withShadow)
        Modifier
            .then(modifier)
            .shadow(
                elevation = elevation,
                spotColor = MaterialTheme.colorScheme.tertiary,
                ambientColor = MaterialTheme.colorScheme.tertiary
            )

    else
        Modifier.then(modifier)


    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)/*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
            )
        }
    }
}