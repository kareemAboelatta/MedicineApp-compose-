package com.example.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.common.R

@Composable
fun AppTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    singleLine: Boolean = true,
    imageVector: ImageVector = Icons.Filled.Info
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = textStyle,

        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = hint)
        },
        singleLine = singleLine,
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },

    )
}


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector,
    singleLine: Boolean = true,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        textStyle = TextStyle(fontSize = 16.sp), // You can adjust the text style as needed
        onValueChange = { newText ->
            onValueChange(newText)
        },
        label = {
            Text(text = hint)
        },
        singleLine = singleLine,
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        },
        trailingIcon = {
            val visibilityIcon =
                if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off ) else painterResource(id = R.drawable.ic_visibility )
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "Toggle Password Visibility"
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action if needed
            }
        )
    )
}


