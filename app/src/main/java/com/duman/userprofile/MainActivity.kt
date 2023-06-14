package com.duman.userprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.duman.userprofile.ui.screens.CreateProfileScreen
import com.duman.userprofile.ui.screens.CreatedProfileScreen
import com.duman.userprofile.ui.screens.ProfileCreationUiState
import com.duman.userprofile.ui.theme.UserProfileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen: String by rememberSaveable { mutableStateOf("topic") }
            var currentState: ProfileCreationUiState by remember {
                mutableStateOf(ProfileCreationUiState())
            }

            when (currentScreen) {
                "topic" -> {
                    CreateProfileScreen { newState ->
                        currentState = newState
                        currentScreen = "other"
                    }
                }

                else -> {
                    CreatedProfileScreen(state = currentState) {
                        currentScreen = "topic"
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserProfileTheme {
        Greeting("Android")
    }
}