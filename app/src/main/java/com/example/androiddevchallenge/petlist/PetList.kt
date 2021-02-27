package com.example.androiddevchallenge.petlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.domain.Pet
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewModelFactory


@Composable
fun PetListScreen(repository: PetRepository, openPet: (String) -> Unit) {
    val viewModel: PetListViewModel = viewModel(
        factory = viewModelFactory { PetListViewModel(repository) }
    )
    val list by viewModel.state.collectAsState()
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { petClicked(id) }
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetListPreview() {
    MyTheme {
        PetList((0..100).map { Pet("$it", "Pet $it", "") })
    }
}
