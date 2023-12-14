package com.example.composemed.auth.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.common.ui.components.AppTextField
import com.example.common.ui.components.PasswordTextField
import com.example.common.ui.components.animations.InfinitelyScaling
import com.example.common.ui.utils.PaddingDimensions
import com.example.composemed.R
import com.example.composemed.Screen


@Composable
fun LoginScreen(navController: NavHostController) {

    val state = rememberLoginDataState()

    val context = LocalContext.current


    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        InfinitelyScaling {
            Icon(
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(id = R.drawable.ic_medical_information),
                contentDescription = "app logo"
            )
        }
        Spacer(modifier = Modifier.height(PaddingDimensions.xxLarge * 3))

        Text(
            text = "Medical Information",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(PaddingDimensions.xxLarge))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.username,
            hint = "Username",
            imageVector = Icons.Outlined.Email,
            onValueChange = {
                state.updateUsername(it)
            })
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.password,
            hint = "Password",
            imageVector = Icons.Filled.Lock,
            onValueChange = {
                state.updatePassword(it)
            }
        )

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(5.dp),
            text = "Forget Password",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(PaddingDimensions.xxLarge))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (state.username.isNotEmpty()) {
                    navController.navigate(Screen.Home.route + "/${state.username}") {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please enter a username",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}


@Composable
fun rememberLoginDataState(): LoginDataState =
    rememberSaveable(saver = LoginDataState.Saver) {
        LoginDataState()
    }

//login state holder
class LoginDataState(
    val initialEmail: String = "",
    val initialPassword: String = "",
) {
    var username by mutableStateOf(initialEmail)
        private set
    var password by mutableStateOf(initialPassword)
        private set

    fun updateUsername(newUsername: String) {
        username = newUsername
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }


    companion object {
        val Saver: Saver<LoginDataState, *> = listSaver(
            save = {
                listOf(it.username, it.password)
            },
            restore = {
                LoginDataState(
                    initialEmail = it[0],
                    initialPassword = it[1],
                )
            }
        )
    }

}
