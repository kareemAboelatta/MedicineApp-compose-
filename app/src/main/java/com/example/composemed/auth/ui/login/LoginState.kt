package com.example.composemed.auth.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


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
