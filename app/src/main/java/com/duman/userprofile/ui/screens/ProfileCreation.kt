package com.duman.userprofile.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duman.userprofile.R
import com.duman.userprofile.ui.elements.ImageContainer
import com.duman.userprofile.ui.elements.ProfileTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateProfileScreen(onSubmitClick: (ProfileCreationUiState) -> Unit) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val profileViewModel: ProfileViewModel = getViewModel()
    val context = LocalContext.current


    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture(),
            onResult = { isSuccess ->
                if (isSuccess) {
                    profileViewModel.onImageURIUpdate(imageUri ?: Uri.EMPTY)
                }

            })
    val collectAsState by profileViewModel.profileCreationUiState.collectAsState()
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.background(color = Color.White),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.profile_creation),
                style = TextStyle.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.submit_p1),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Gray
            )

            ImageContainer(
                onClick = {
                    val uri = ComposeFileProvider.getImageUri(context)
                    imageUri = uri
                    singlePhotoPickerLauncher.launch(uri)
                },
                imageUri = collectAsState.selectedImageUri,
                modifier = Modifier.align(CenterHorizontally),
                isError = collectAsState.isImageError
            )
            MyOutlinedInputField(state = collectAsState, viewModel = profileViewModel)
            Spacer(modifier = Modifier.weight(.4f))
            Button(
                onClick = {
                    if (profileViewModel.check()) {
                        onSubmitClick(collectAsState)
                    }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp),
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOutlinedInputField(
    state: ProfileCreationUiState, viewModel: ProfileViewModel, modifier: Modifier = Modifier
) {
    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.Gray,
        placeholderColor = Color.Gray,
        focusedBorderColor = Color.Gray,
        cursorColor = Color.Gray
    )

    ProfileTextField(
        data = state.firstName, onValueChange = viewModel::onNameUpdate, label = stringResource(
            id = R.string.first_name
        )
    )
    ProfileTextField(
        data = state.eMailAddress, onValueChange = viewModel::oneMailUpdate,
        label = stringResource(
            id = R.string.email_address
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
    )



    OutlinedTextField(
        value = state.password.successMessage,
        onValueChange = viewModel::onPasswordUpdate,
        label = {
            Text(
                text = stringResource(R.string.password),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        },
        colors = customTextFieldColors,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (state.passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = { viewModel.onPasswordVisibilityUpdate(!state.passwordVisible) }) {
                Icon(
                    imageVector = if (state.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (state.passwordVisible) stringResource(R.string.hide_password) else stringResource(
                        R.string.show_password
                    )
                )
            }
        },
        isError = state.password.isError(),
        supportingText = state.password.GetSupportText()
    )

    ProfileTextField(
        data = state.webSite, onValueChange = viewModel::onWebsiteUpdate,
        label = stringResource(
            id = R.string.website_address
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
    )
}

@Preview
@Composable
fun PreviewMy() {
    CreateProfileScreen({ uri -> })
}