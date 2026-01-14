package com.example.hope.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.hope.data.room.event.CurrentUserEvent
import com.example.hope.data.room.state.CurrentUserState
import com.example.hope.screen.HomeScreen
import com.example.hope.screen.LogIn
import com.example.hope.screen.SignUp
import com.example.hope.screen.TermsAndConditions

@Composable
fun HopeApp(
    state: CurrentUserState,
    onEvent: (CurrentUserEvent) -> Unit
){
    Surface(
        modifier= Modifier.fillMaxSize(),
        color = Color.White
    ){
        Crossfade(targetState = AppRouter.currentScreen, label = "") { currentState ->
            when(currentState.value){
                is Screen.SignUpScreen -> {
                    SignUp(state,onEvent)
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditions()
                }
                is Screen.LogInScreen -> {
                    LogIn(state,onEvent)
                }
                Screen.HomeScreen -> {
                    HomeScreen(state)
                }
            }
        }
    }
}