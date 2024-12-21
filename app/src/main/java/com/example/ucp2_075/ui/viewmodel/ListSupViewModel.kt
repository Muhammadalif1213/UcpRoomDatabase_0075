package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_075.data.entity.Supplier
import com.example.ucp2_075.repository.RepoSup
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ListSupViewModel(
    private val repoSup: RepoSup
) :ViewModel() {
    val  listUiState: StateFlow<ListUiState> = repoSup.getAllSup()
        .filterNotNull()
        .map {
            ListUiState(
                listSup = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(ListUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ListUiState(
                    isLoading = false,
                    isError = false,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListUiState(
                isLoading = true
            )
        )
}

data class ListUiState(
    val listSup: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
