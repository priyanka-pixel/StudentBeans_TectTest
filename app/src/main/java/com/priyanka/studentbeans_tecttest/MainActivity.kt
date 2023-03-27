package com.priyanka.studentbeans_tecttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.priyanka.studentbeans_tecttest.authorisation.navigation.Navigation
import com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen.SignInViewModel
import com.priyanka.studentbeans_tecttest.ui.theme.StudentBeans_TectTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = SignInViewModel::class.java)
            StudentBeans_TectTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(loginViewModel = loginViewModel)

                }
            }
        }
    }
}

