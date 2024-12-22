package com.example.ucp2_075.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_075.data.entity.Barang
import com.example.ucp2_075.repository.RepoBrg
import com.example.ucp2_075.ui.navigation.DestinasiDetailBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoBrg: RepoBrg
) : ViewModel(){
    var updateUitate by mutableStateOf(BrgUIState())
        private  set

    private val _id_barang: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.ID_BRG])

    init {
        viewModelScope.launch {
            updateUitate = repoBrg.getBrg(_id_barang)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent){
        updateUitate = updateUitate.copy(
            barangEvent = barangEvent,
        )
    }

    fun ValidateField(): Boolean{
        val event = updateUitate.barangEvent
        val errorState = FormErrorBrgState(
            id_barang = if (event.id_barang.isNotEmpty()) null else "ID Barang tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            nama_sup = if (event.nama_sup.isNotEmpty()) null else "Nama Supplier tidak boleh kosong"
        )
        updateUitate = updateUitate.copy(isEntryValid = errorState)
        return errorState.isBrgValid()
    }

    fun updateData(){
        val currentEvent = updateUitate.barangEvent

        if(ValidateField()){
            viewModelScope.launch {
                try {
                    repoBrg.updateBrg(currentEvent.toBarangEntity())
                    updateUitate = updateUitate.copy(
                        snackbarMessage = "Data berhasil diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorBrgState()
                    )
                    println("snackbarMessage diatur: ${updateUitate.snackbarMessage}")
                } catch (e: Exception) {
                    updateUitate = updateUitate.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        }else {
            updateUitate = updateUitate.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }

    }
    fun resetSnackBarMessage(){
        updateUitate = updateUitate.copy(snackbarMessage = null)
    }
}



fun Barang.toUIStateBrg(): BrgUIState = BrgUIState(
    barangEvent = this.toDetailBrgUiEvent(),
)