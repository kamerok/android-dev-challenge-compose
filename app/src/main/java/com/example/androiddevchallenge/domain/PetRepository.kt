package com.example.androiddevchallenge.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class PetRepository {

    private val petsFlow = MutableStateFlow((0..100).map { Pet("$it", "Pet $it", "") })

    fun getPets(): Flow<List<Pet>> = petsFlow

    fun getPet(id: String): Pet? = petsFlow.value.find { it.id == id }

}
