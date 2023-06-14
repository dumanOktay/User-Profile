package com.duman.userprofile.ui.elements

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duman.userprofile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageContainer(
    onClick: () -> Unit,
    imageUri: Uri,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    Card(
        elevation = CardDefaults.outlinedCardElevation(),
        modifier = modifier
            .size(width = 180.dp, height = 220.dp)
            .padding(16.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.LightGray),
        onClick = onClick
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.tap_to_add_avatar),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(.8f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
                color = if (isError) Color.Red else Color.Gray
            )
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }

}