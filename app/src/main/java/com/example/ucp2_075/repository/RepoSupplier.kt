package com.example.ucp2_075.repository

import com.example.ucp2_075.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepoSup {
    suspend fun insertSup(supplier: Supplier)

    fun getAllSup() : Flow<List<Supplier>>

    fun getSup(id_sup: String): Flow<Supplier>
}