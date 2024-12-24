package com.example.ucp2_075.repository

import com.example.ucp2_075.data.dao.BarangDao
import com.example.ucp2_075.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepoBrg(
    private val barangDao: BarangDao
): RepoBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBrg(idBarang: Int): Flow<Barang> {
        return barangDao.getBarang(idBarang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        return barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        return barangDao.updateBarang(barang)
    }

}