package com.example.ucp2_075.ui.viewmodel

import com.example.ucp2_075.data.entity.Supplier

data class ListUiState(
    val listSup: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
