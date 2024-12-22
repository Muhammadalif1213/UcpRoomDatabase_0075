package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
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
        initializer {
            InsertSupViewModel(
                TokoApp().containerApp.repositorySup
            )
        }
        initializer {
            ListSupViewModel(
                TokoApp().containerApp.repositorySup
            )
        }
        initializer {
            InsertBrgViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            ListBrgViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }

    }
}


fun CreationExtras.TokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as TokoApp)