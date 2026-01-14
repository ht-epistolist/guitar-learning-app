package com.example.hope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.hope.app.HopeApp
import com.example.hope.data.room.LocalDatabase
import com.example.hope.data.room.view_model.CurrentUserViewModel
import com.example.hope.ui.theme.HopeTheme

class MainActivity : ComponentActivity() {
    private val database by lazy { //create the database
        Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java,
            "local.db"
        ).build()
    }
    private val viewModel by viewModels<CurrentUserViewModel>(
        factoryProducer = {//initialize the view model and add parameters
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CurrentUserViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HopeTheme {
                val state by viewModel.state.collectAsState()
                HopeApp(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}