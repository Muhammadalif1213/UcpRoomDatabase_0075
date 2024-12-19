package com.example.ucp2_075.repository

import com.example.ucp2_075.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepoBarang {
    suspend fun insertBarang(barang: Barang)

    fun getAllBarang() : Flow<List<Barang>>

    fun getBarang(id_barang: String) : Flow<List<Barang>>

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}