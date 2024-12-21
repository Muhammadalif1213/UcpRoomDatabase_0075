package com.example.ucp2_075.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_075.ui.customWidget.TopAppBar
import com.example.ucp2_075.ui.viewmodel.BarangEvent
import com.example.ucp2_075.ui.viewmodel.BrgUIState
import com.example.ucp2_075.ui.viewmodel.FormErrorBrgState
import com.example.ucp2_075.ui.viewmodel.InsertBrgViewModel
import com.example.ucp2_075.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onNavigate: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessageBrg()
            }
        }
    }
    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang"
            )
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateBrgState(updateEvent)
                },
                onClick = {
                    viewModel.saveDataBrg()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorBrgState: FormErrorBrgState = FormErrorBrgState(),
    modifier: Modifier = Modifier
){
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id_barang,
            onValueChange = {
                onValueChange(barangEvent.copy(id_barang = it))
            },
            label = { Text("ID Barang")},
            isError = errorBrgState.id_barang != null,
            placeholder = { Text("Masukan ID Barang")}
        )
        Text(
            text = errorBrgState.id_barang ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama Barang")},
            isError = errorBrgState.nama != null,
            placeholder = { Text("Masukan Nama Barang")}
        )
        Text(
            text = errorBrgState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi Barang")},
            isError = errorBrgState.deskripsi != null,
            placeholder = { Text("Masukan Deskripsi Barang")}
        )
        Text(
            text = errorBrgState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga Barang")},
            isError = errorBrgState.harga != null,
            placeholder = { Text("Masukan Harga Barang")}
        )
        Text(
            text = errorBrgState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok Barang")},
            isError = errorBrgState.stok != null,
            placeholder = { Text("Masukan Stok Barang")}
        )
        Text(
            text = errorBrgState.stok ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama_sup,
            onValueChange = {
                onValueChange(barangEvent.copy(nama_sup = it))
            },
            label = { Text("Nama Supplier")},
            isError = errorBrgState.nama_sup != null,
            placeholder = { Text("Masukan Nama Supplier")}
        )
        Text(
            text = errorBrgState.nama_sup ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorBrgState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()

        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}