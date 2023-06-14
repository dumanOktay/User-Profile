package com.duman.userprofile.ui.screens

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.duman.userprofile.R
import java.io.File

class ComposeFileProvider : FileProvider(
    R.xml.filepaths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = "com.duman.userprofile.ui.screens.fileprovider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}