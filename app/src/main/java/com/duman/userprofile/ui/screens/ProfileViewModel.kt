package com.duman.userprofile.ui.screens

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    val profileCreationUiState = MutableStateFlow(ProfileCreationUiState())

    fun onNameUpdate(newValue: String) {
        profileCreationUiState.update {
            it.copy(firstName = FieldsData(successMessage = newValue, errorMessage = null))
        }
    }

    fun oneMailUpdate(newValue: String) {
        profileCreationUiState.update {
            it.copy(eMailAddress = FieldsData(successMessage = newValue, errorMessage = null))
        }
    }

    fun onPasswordUpdate(newValue: String) {
        profileCreationUiState.update {
            it.copy(password = FieldsData(successMessage = newValue, errorMessage = null))
        }
    }

    fun onWebsiteUpdate(newValue: String) {
        profileCreationUiState.update {
            it.copy(webSite = FieldsData(successMessage = newValue, errorMessage = null))
        }
    }

    fun onPasswordVisibilityUpdate(newValue: Boolean) {
        profileCreationUiState.update {
            it.copy(passwordVisible = newValue)
        }
    }

    fun onImageURIUpdate(newValue: Uri) {
        profileCreationUiState.update {
            it.copy(selectedImageUri = newValue)
        }
    }

    fun check(): Boolean {
        var isSuccess = true

        val currentState = profileCreationUiState.value
        if (currentState.selectedImageUri == Uri.EMPTY) {
            isSuccess = false
            profileCreationUiState.update {
                it.copy(isImageError = true)
            }
        } else {
            profileCreationUiState.update {
                it.copy(isImageError = false)
            }
        }

        if ((currentState.firstName.successMessage.isEmpty()) || (currentState.firstName.errorMessage != null)) {
            isSuccess = false
            profileCreationUiState.update {
                it.copy(
                    firstName = FieldsData(
                        successMessage = "",
                        errorMessage = "Please enter a" +
                                "valid name"
                    )
                )
            }
        }

        if ((currentState.eMailAddress.successMessage.length < 3) ||
            (!currentState.eMailAddress.successMessage.contains(
                "@"
            )) ||
            (!currentState.eMailAddress.successMessage.contains(
                "."
            )) ||
            (currentState.eMailAddress.errorMessage != null)
        ) {
            isSuccess = false
            profileCreationUiState.update {
                it.copy(
                    eMailAddress = FieldsData(
                        successMessage = "",
                        errorMessage = "Please enter a valid email"
                    )
                )
            }
        }
        if ((currentState.password.successMessage.length < 6) || (currentState.password.errorMessage != null)) {
            isSuccess = false
            profileCreationUiState.update {
                it.copy(
                    password = FieldsData(
                        successMessage = "",
                        errorMessage = "Please enter a password. Min 6 char"
                    )
                )
            }
        }

        if ((currentState.webSite.successMessage == "") ||
            (!currentState.webSite.successMessage.contains(
                "."
            )) ||
            (currentState.webSite.errorMessage != null)) {
            isSuccess = false
            profileCreationUiState.update {
                it.copy(
                    webSite = FieldsData(
                        successMessage = "",
                        errorMessage = "Please enter a web address"
                    )
                )
            }
        }


        return isSuccess
    }


}