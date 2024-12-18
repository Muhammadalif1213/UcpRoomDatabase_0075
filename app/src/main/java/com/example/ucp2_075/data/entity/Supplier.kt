package com.example.ucp2_075.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplier")
data class Supplier(
    @PrimaryKey
    val id_sup: String,
    val namaSup: String,
    val kontakSup: String,
    val alamatSup: String
)
