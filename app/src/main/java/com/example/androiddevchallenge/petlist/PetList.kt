package com.example.androiddevchallenge.petlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.Pet
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewModelFactory


@Composable
fun PetListScreen(
    repository: PetRepository,
    setupAppBar: (String, Boolean) -> Unit,
    openPet: (String) -> Unit
) {
    LaunchedEffect(setupAppBar) {
        setupAppBar("Pets", false)
    }

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dog),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .background(shape = CircleShape, color = MaterialTheme.colors.secondary)
                    .padding(4.dp),
                tint = MaterialTheme.colors.onSecondary
            )
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PetList((0..100).map { Pet("$it", "Pet $it", "") })
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PetList((0..100).map { Pet("$it", "Pet $it", "") })
    }
}
