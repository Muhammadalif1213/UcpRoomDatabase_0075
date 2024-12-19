package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_075.TokoApp


object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeAppViewModel(
            )
        }
    }
}

fun CreationExtras.TokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as TokoApp)