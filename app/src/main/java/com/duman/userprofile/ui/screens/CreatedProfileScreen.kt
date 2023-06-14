package com.duman.userprofile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duman.userprofile.R
import com.duman.userprofile.ui.elements.ImageContainer
import com.duman.userprofile.ui.navigation.NavigationScreen

@Composable
fun CreatedProfileScreen(state: ProfileCreationUiState, backAction: () -> Unit) {
    NavigationScreen(title = state.firstName.successMessage, backAction = backAction) {

        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Hello " + state.firstName.successMessage + "!",
                    style = TextStyle.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(R.string.submit_p2),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                ImageContainer(
                    onClick = backAction,
                    imageUri = state.selectedImageUri,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )


                Text(
                    text = state.webSite.successMessage,
                    fontSize = 18.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.password.successMessage,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.eMailAddress.successMessage,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.weight(.4f))
                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(8.dp),
                ) {
                    Text(text = stringResource(R.string.sign_in))
                }

            }


        }
    }
}