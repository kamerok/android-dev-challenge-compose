/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.pet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewModelFactory

@Composable
fun PetScreen(id: String, repository: PetRepository, setupAppBar: (String, Boolean) -> Unit) {
    val viewModel: PetViewModel = viewModel(
        factory = viewModelFactory { PetViewModel(id, repository) }
    )
    val pet = viewModel.state.collectAsState(null).value
    if (pet != null) {
        LaunchedEffect(true) {
            setupAppBar(pet.name, true)
        }
        PetContent(pet.description)
    }
}

@Composable
private fun PetContent(description: String) {
    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .background(color = MaterialTheme.colors.secondary)
                    .padding(4.dp),
                tint = MaterialTheme.colors.onSecondary,
                painter = painterResource(id = R.drawable.ic_dog),
                contentDescription = null
            )
            Text(
                text = description,
                Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PetScreenPreview() {
    MyTheme {
        PetContent(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
        )
    }
}
