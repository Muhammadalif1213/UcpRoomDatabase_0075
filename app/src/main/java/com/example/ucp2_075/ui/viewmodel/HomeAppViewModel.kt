package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow


class HomeAppViewModel(
): ViewModel(){
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)