package com.example.androiddevchallenge.petlist

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.Pet
import com.example.androiddevchallenge.domain.PetRepository
import kotlinx.coroutines.flow.Flow


class PetListViewModel(repository: PetRepository) : ViewModel() {

    val state: Flow<List<Pet>> = repository.getPets()

}
