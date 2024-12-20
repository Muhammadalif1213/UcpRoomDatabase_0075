package com.example.ucp2_075.ui.viewmodel

import com.example.ucp2_075.data.entity.Barang




data class FormErrorBrgState(
    val id_barang: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val nama_sup: String? = null,

    ){
    fun isValid(): Boolean {
        return id_barang == null && nama == null && deskripsi == null &&
                harga == null && stok == null && nama_sup == null
    }
}


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