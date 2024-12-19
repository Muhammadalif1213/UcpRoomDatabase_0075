package com.example.ucp2_075.repository

import com.example.ucp2_075.data.dao.BarangDao
import com.example.ucp2_075.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepoBarang(
    private val barangDao: BarangDao
): RepoBarang {
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBarang(id_barang: String): Flow<Barang> {
        return barangDao.getBarang(id_barang)
    }

    override suspend fun deleteBarang(barang: Barang) {
        return barangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        return barangDao.updateBarang(barang)
    }

}