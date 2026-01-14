package com.example.hope.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hope.R
import com.example.hope.app.AppRouter
import com.example.hope.app.Screen
import com.example.hope.components.HeadText
import com.example.hope.data.room.state.CurrentUserState
import com.example.hope.ui.theme.colorLight

@Composable
fun HomeScreen(
    state: CurrentUserState
) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(colorLight)
        .padding(16.dp)){
        LazyColumn(){
            items(state.currentUser) { user ->
                HeadText(value = "${user.name}, ${user.password}")
            }
        }
    }
    BackHandler(true){
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}