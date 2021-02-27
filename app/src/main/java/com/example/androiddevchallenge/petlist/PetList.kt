package com.example.androiddevchallenge.petlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


data class Pet(
    val id: String,
    val name: String
)

@Composable
fun PetListScreen(openPet: (String) -> Unit) {
    val pets = remember { mutableStateOf((0..100).map { Pet("$it", "Pet $it") }) }
    PetList(pets.value, openPet)
}

@Composable
private fun PetList(pets: List<Pet>, petClicked: (String) -> Unit = {}) {
    Surface {
        LazyColumn {
            items(pets) { pet ->
                PetItem(pet = pet, petClicked = petClicked)
            }
        }
    }
}

@Composable
private fun PetItem(
    pet: Pet,
    petClicked: (String) -> Unit
) {
    Text(
        text = pet.name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { petClicked(pet.id) }
            .padding(16.dp)
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetListPreview() {
    MyTheme {
        PetList((0..100).map { Pet("$it", "Pet $it") })
    }
}
