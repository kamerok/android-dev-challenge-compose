package com.example.androiddevchallenge.petlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.androiddevchallenge.domain.Pet
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun PetListScreen(repository: PetRepository, openPet: (String) -> Unit) {
    val viewModel: PetListViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PetListViewModel(repository) as T
    })
    val list = viewModel.state.collectAsState(emptyList()).value
    PetList(list, openPet)
}

@Composable
private fun PetList(pets: List<Pet>, petClicked: (String) -> Unit = {}) {
    Surface {
        LazyColumn {
            items(pets, key = { it.id }) { pet ->
                PetItem(pet.id, pet.name, petClicked)
            }
        }
    }
}

@Composable
private fun PetItem(
    id: String,
    name: String,
    petClicked: (String) -> Unit
) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { petClicked(id) }
            .padding(16.dp)
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetListPreview() {
    MyTheme {
        PetList((0..100).map { Pet("$it", "Pet $it", "") })
    }
}
