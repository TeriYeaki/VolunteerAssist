package com.example.volunteerassist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xffffcc00)),
        contentAlignment = Alignment.Center

    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Welcome to Navigation app", style =
            MaterialTheme.typography.bodyLarge)
        }
    }
}