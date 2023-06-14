package com.duman.userprofile.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.duman.userprofile.ui.screens.FieldsData
import com.duman.userprofile.ui.screens.GetSupportText
import com.duman.userprofile.ui.screens.isError


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    data: FieldsData,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.Gray,
        placeholderColor = Color.Gray,
        focusedBorderColor = Color.Gray,
        cursorColor = Color.Gray
    )

    OutlinedTextField(
        value = data.successMessage,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        },
        colors = customTextFieldColors,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        isError = data.isError(),
        supportingText = data.GetSupportText()
    )
}