package com.example.ucp2_075.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val id_barang: String,
    val nama: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val nama_sup: String,
)
