package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_075.data.entity.Barang
import com.example.ucp2_075.repository.RepoBrg
import com.example.ucp2_075.ui.navigation.DestinasiDetailBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoBrg: RepoBrg,
): ViewModel(){
    private val _idBarang: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.IDBRG])

    val detailBrgUIstate: StateFlow<DetailBrgUiState> = repoBrg.getBrg(_idBarang.toInt())
        .filterNotNull()
        .map { DetailBrgUiState(
            detailBarangUiEvent = it.toDetailBrgUiEvent(),
            isLoading = false,
        )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailBrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailBrgUiState(
                isLoading = true,
            ),
        )
    fun deleteBrg(){
        detailBrgUIstate.value.detailBarangUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repoBrg.deleteBrg(it)
            }
        }
    }
}



data class DetailBrgUiState(
    val detailBarangUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isBrgUiEventEmpty: Boolean
        get() = detailBarangUiEvent == BarangEvent()

    val isBrgUiEventNotEmpty: Boolean
        get() = detailBarangUiEvent != BarangEvent()
}



fun Barang.toDetailBrgUiEvent() : BarangEvent{
    return  BarangEvent(
        idBarang = idBarang,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok.toString(),
        nama_sup = nama_sup
    )
}