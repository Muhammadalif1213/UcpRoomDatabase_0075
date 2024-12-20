package com.example.ucp2_075.ui.viewmodel

import com.example.ucp2_075.data.entity.Barang


fun BarangEvent.toBarangEntity(): Barang = Barang(
    id_barang = id_barang,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    nama_sup = nama_sup
)

data class BarangEvent(
    val id_barang: String = "" ,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val nama_sup: String = "",
)