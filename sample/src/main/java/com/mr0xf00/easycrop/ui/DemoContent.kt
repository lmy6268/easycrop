package com.mr0xf00.easycrop.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mr0xf00.easycrop.AspectRatio
import com.mr0xf00.easycrop.CropState
import com.mr0xf00.easycrop.CropperLoading
import com.mr0xf00.easycrop.CropperStyle
import com.mr0xf00.easycrop.ui.theme.EasyCropTheme
import kotlin.math.abs

@Composable
fun DemoContent(
    cropState: CropState?,
    loadingStatus: CropperLoading?,
    selectedImage: ImageBitmap?,
    onPick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (cropState != null) {
        EasyCropTheme(darkTheme = true) {
            ImageCropperDialog(
                state = cropState,
                style = CropperStyle(
                    overlay = Color.White.copy(alpha = 0.5f),
                    aspects = mutableListOf(
                        AspectRatio(1, 1),
                        AspectRatio(9, 16),
                        AspectRatio(3, 4),
                        selectedImage?.run {
                            fun gcd(a: Int, b: Int): Int {
                                return if (b == 0) abs(a) else gcd(b, a % b)
                            }

                            val divisor = gcd(width, height)
                            val x = width / divisor
                            val y = height / divisor
                            AspectRatio(x, y)
                        } ?: AspectRatio(0, 0)
                    )
                ),

                )
        }
    }
    if (cropState == null && loadingStatus != null) {
        LoadingDialog(status = loadingStatus)
    }
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedImage != null) Image(
            bitmap = selectedImage, contentDescription = null,
            modifier = Modifier.weight(1f)
        ) else Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
            Text("No image selected !")
        }
        Button(onClick = onPick) { Text("Choose Image") }
    }
}
