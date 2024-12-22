package com.example.ucp2_075.ui.viewmodel


import com.example.ucp2_075.data.entity.Barang

data class ListBrgUIstate(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)