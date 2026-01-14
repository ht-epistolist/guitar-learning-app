package com.example.hope.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hope.components.Header
import com.example.hope.components.Navigation
import com.example.hope.components.SearchField
import com.example.hope.ui.theme.colorLight

@Composable
fun SearchScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorLight)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header {
                
            }
            Navigation {
                
            }
            SearchField(onChange = {}, onSubmit = { /*TODO*/ }) {

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(state.songs.size){ index ->
                    SongItem(
                        state = state,
                        index = index,
                        onEvent = onEvent)
                }
            }
        }        
    }
}

@Preview
@Composable
fun S(){

    SearchScreen()
}