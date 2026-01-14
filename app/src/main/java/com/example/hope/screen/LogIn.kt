package com.example.hope.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hope.R
import com.example.hope.app.AppRouter
import com.example.hope.app.Screen
import com.example.hope.components.ButtonComponent
import com.example.hope.components.ClickableTextComponent
import com.example.hope.components.HeadText
import com.example.hope.components.InputTextField
import com.example.hope.components.NormalText
import com.example.hope.components.PasswordTextField
import com.example.hope.data.room.event.CurrentUserEvent
import com.example.hope.data.room.state.CurrentUserState
import com.example.hope.ui.theme.colorLight
import com.example.hope.ui.theme.iconEmail
import com.example.hope.ui.theme.iconPassword

@Composable
fun LogIn(
    state: CurrentUserState,
    onEvent: (CurrentUserEvent) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorLight)
            .padding(28.dp)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorLight)) {
            NormalText(value = stringResource(id = R.string.login))
            HeadText(value = stringResource(id = R.string.login_account))
            Spacer(modifier = Modifier.height(40.dp))
            InputTextField(labelValue = stringResource(id = R.string.email),
                iconValue = iconEmail,
                eventOnValueChange = {
                    onEvent(CurrentUserEvent.SetEmail(it))
                })
            PasswordTextField(labelValue = stringResource(id = R.string.password),
                iconValue = iconPassword)
            Spacer(modifier = Modifier.height(40.dp))
            ClickableTextComponent(value = "",
                clickableValue = stringResource(id = R.string.forgot), onTextSelected = {

                })
            Spacer(modifier = Modifier.height(120.dp))
            ButtonComponent(value = stringResource(id = R.string.login),
                eventOnClick = {

                })
            ClickableTextComponent(value = stringResource(id = R.string.not_have_an_account),
                clickableValue = stringResource(id = R.string.signup), onTextSelected = {
                    AppRouter.navigateTo(Screen.SignUpScreen)
                })
        }
        BackHandler(true){
            AppRouter.navigateTo(Screen.SignUpScreen)
        }
    }
}

@Preview
@Composable
fun DefaultPreviewLogIn(){
    //LogIn()
}