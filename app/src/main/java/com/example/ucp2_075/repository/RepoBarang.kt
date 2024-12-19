package com.example.ucp2_075.repository

import com.example.ucp2_075.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepoBarang {
    suspend fun insertBrg(barang: Barang)

    fun getAllBrg() : Flow<List<Barang>>

    fun getBrg(id_barang: String) : Flow<Barang>

    suspend fun deleteBrg(barang: Barang)

    suspend fun updateBrg(barang: Barang)
}