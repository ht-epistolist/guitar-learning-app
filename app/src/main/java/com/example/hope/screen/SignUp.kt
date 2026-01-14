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
import com.example.hope.components.CheckboxComponent
import com.example.hope.components.ClickableTextComponent
import com.example.hope.components.HeadText
import com.example.hope.components.InputPasswordTextField
import com.example.hope.components.InputTextField
import com.example.hope.components.NormalText
import com.example.hope.components.PasswordTextField
import com.example.hope.components.TinyText
import com.example.hope.data.room.event.CurrentUserEvent
import com.example.hope.data.room.state.CurrentUserState
import com.example.hope.ui.theme.colorLight
import com.example.hope.ui.theme.iconEmail
import com.example.hope.ui.theme.iconName
import com.example.hope.ui.theme.iconPassword

@Composable
fun SignUp(
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
            NormalText(value = stringResource(id = R.string.hello))
            HeadText(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            InputTextField(labelValue = stringResource(id = R.string.first_name),
                iconValue = iconName,
                eventOnValueChange = {//on value change set the name in a table
                    onEvent(CurrentUserEvent.SetName(it))
                })
            InputTextField(labelValue = stringResource(id = R.string.last_name),
                iconValue = iconName,
                eventOnValueChange = {//on value change set the username in a table
                    onEvent(CurrentUserEvent.SetUsername(it))
                })
            InputTextField(labelValue = stringResource(id = R.string.email),
                iconValue = iconEmail,
                eventOnValueChange = {
                    onEvent(CurrentUserEvent.SetEmail(it))
                })
            InputPasswordTextField(labelValue = stringResource(id = R.string.password),
                iconValue = iconPassword,
                eventOnValueChange = {
                    onEvent(CurrentUserEvent.SetPassword(""))
                })
            CheckboxComponent(value = stringResource(id = R.string.terms_conditions),
                clickableValue = stringResource(id = R.string.terms_conditions2),
                onTextSelected = {
                    AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                })
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = stringResource(id = R.string.signup),
                eventOnClick = {
                    onEvent(CurrentUserEvent.SaveCurrentUser)
                    AppRouter.navigateTo(Screen.HomeScreen)
                })
            ClickableTextComponent(value = stringResource(id = R.string.have_an_account),
                clickableValue = stringResource(id = R.string.login), onTextSelected = {
                    AppRouter.navigateTo(Screen.LogInScreen)
                })
        }
        BackHandler(true){}
    }
}

//@Preview
@Composable
fun DefaultPreviewSignUp(){

    Surface {
        Column {
            InputPasswordTextField(labelValue = "kh", iconValue = iconPassword, eventOnValueChange = {})
            TinyText("jkhoi",false)
        }


    }
}