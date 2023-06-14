package com.duman.userprofile.ui.screens

import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class ProfileCreationUiState(
    val firstName: FieldsData = FieldsData(),
    val password: FieldsData = FieldsData(),
    val eMailAddress: FieldsData = FieldsData(),
    val webSite: FieldsData = FieldsData(),
    val passwordVisible: Boolean = true,
    val selectedImageUri: Uri = Uri.EMPTY,
    val isImageError: Boolean = false
)

data class FieldsData(val successMessage: String = "", val errorMessage: String? = null)

fun FieldsData.isError() = errorMessage != null

fun FieldsData.GetSupportText(): @Composable (() -> Unit)? = if (errorMessage != null) {
    { Text(text = errorMessage) }
} else null

