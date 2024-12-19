package com.example.ucp2_075.repository

import com.example.ucp2_075.data.dao.BarangDao
import com.example.ucp2_075.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepoBarang(
    private val barangDao: BarangDao
): RepoBarang {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBrg(id_barang: String): Flow<Barang> {
        return barangDao.getBarang(id_barang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        return barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        return barangDao.updateBarang(barang)
    }

}