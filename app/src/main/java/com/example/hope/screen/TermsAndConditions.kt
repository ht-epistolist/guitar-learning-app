package com.example.hope.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.hope.components.HeadText
import com.example.hope.ui.theme.colorLight

@Composable
fun TermsAndConditions() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(colorLight)
        .padding(16.dp)){
    HeadText(value = stringResource(id = R.string.terms_conditions2))
    }
    BackHandler(true){
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun TCPreview(){
    TermsAndConditions()
}