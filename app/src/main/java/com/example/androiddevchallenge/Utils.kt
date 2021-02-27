package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
fun <T> viewModelFactory(factory: () -> T): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            factory() as T
    }
