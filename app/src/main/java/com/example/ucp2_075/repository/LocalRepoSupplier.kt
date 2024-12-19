package com.example.ucp2_075.repository

import com.example.ucp2_075.data.dao.SupplierDao
import com.example.ucp2_075.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepoSupplier(
    private val supplierDao: SupplierDao
): RepoSupplier {
    override suspend fun insertSup(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getAllSup(): Flow<List<Supplier>> {
        return supplierDao.getAllSupplier()
    }

    override fun getSup(id_sup: String): Flow<Supplier> {
        return supplierDao.getSupplier(id_sup)
    }

}