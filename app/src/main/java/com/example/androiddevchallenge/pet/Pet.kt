package com.example.androiddevchallenge.pet

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun PetScreen(id: String) {
    Surface {
        Text(
            text = id,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetScreenPreview() {
    MyTheme {
        PetScreen("id")
    }
}
