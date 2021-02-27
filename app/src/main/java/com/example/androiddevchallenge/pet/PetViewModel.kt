package com.example.androiddevchallenge.pet

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.Pet
import com.example.androiddevchallenge.domain.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PetViewModel(id: String, repository: PetRepository) : ViewModel() {
    val state: Flow<Pet?> = flow { emit(repository.getPet(id)) }
}
