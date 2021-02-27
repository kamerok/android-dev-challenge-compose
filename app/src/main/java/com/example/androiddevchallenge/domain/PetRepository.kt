package com.example.androiddevchallenge.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PetRepository {

    private val petsFlow = MutableStateFlow((0..100).map { Pet("$it", "Pet $it", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum") })

    fun getPets(): StateFlow<List<Pet>> = petsFlow

    fun getPet(id: String): Pet? = petsFlow.value.find { it.id == id }

}
