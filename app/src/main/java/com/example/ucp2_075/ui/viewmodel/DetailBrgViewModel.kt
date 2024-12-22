package com.example.ucp2_075.ui.viewmodel

import com.example.ucp2_075.data.entity.Barang

data class DetailBrgUiState(
    val detailBarangUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isBrgUiEventEmpty: Boolean
        get() = detailBarangUiEvent == BarangEvent()

    val isBrgUiEventNotEmpty: Boolean
        get() = detailBarangUiEvent != BarangEvent()
}



fun Barang.toDetailBrgUiEvent() : BarangEvent{
    return  BarangEvent(
        id_barang = id_barang,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        nama_sup = nama_sup
    )
}