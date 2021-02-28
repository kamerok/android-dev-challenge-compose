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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.domain.PetRepository
import com.example.androiddevchallenge.pet.PetScreen
import com.example.androiddevchallenge.petlist.PetListScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProvideWindowInsets {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column {
        val repository = remember { PetRepository() }
        val navController = rememberNavController()

        var topBarState by remember { mutableStateOf("Pets" to false) }

        TopAppBar(
            title = { Text(text = topBarState.first) },
            navigationIcon = if (topBarState.second) {
                {
                    val backDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
                    IconButton(onClick = { backDispatcherOwner.onBackPressedDispatcher.onBackPressed() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            } else null,
        )
        NavHost(navController, "pets") {
            composable("pets") {
                PetListScreen(
                    repository = repository,
                    setupAppBar = { title, isBackEnabled -> topBarState = title to isBackEnabled },
                    openPet = { navController.navigate("pets/$it") }
                )
            }
            composable(
                "pets/{petId}",
                arguments = listOf(navArgument("petId") { type = NavType.StringType })
            ) { entry ->
                PetScreen(
                    id = entry.arguments?.getString("petId")!!,
                    repository = repository
                ) { title, isBackEnabled ->
                    topBarState = title to isBackEnabled
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
