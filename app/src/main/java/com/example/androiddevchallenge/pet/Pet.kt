package com.example.androiddevchallenge.pet

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun PetScreen(id: String, repository: PetRepository) {
    val viewModel: PetViewModel = viewModel(factory = object : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PetViewModel(id, repository) as T
    })
    val pet = viewModel.state.collectAsState(null).value
    if (pet != null) {
        PetContent(pet.name, pet.description)
    }
}

@Composable
private fun PetContent(name: String, description: String) {
    Surface {

        Text(
            text = name,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetScreenPreview() {
    MyTheme {
        PetContent("name", "description")
    }
}
