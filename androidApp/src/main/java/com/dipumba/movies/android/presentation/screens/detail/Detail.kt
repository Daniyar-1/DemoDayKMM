package com.dipumba.movies.android.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dipumba.movies.android.R

@Composable
fun Detail() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .background(color = Color.LightGray.copy(alpha = 0.85f))
        ) {
            Image(
                painter = painterResource(R.drawable.il_ok),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(40.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}