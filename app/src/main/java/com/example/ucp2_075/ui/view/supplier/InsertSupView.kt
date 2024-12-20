package com.example.ucp2_075.ui.view.supplier

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_075.ui.customWidget.TopAppBar
import com.example.ucp2_075.ui.viewmodel.FormErrorState
import com.example.ucp2_075.ui.viewmodel.InsertSupViewModel
import com.example.ucp2_075.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_075.ui.viewmodel.SupUIState
import com.example.ucp2_075.ui.viewmodel.SupplierEvent
import kotlinx.coroutines.launch

@Composable
fun InsertSupView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSupViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ) { padding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySup(
                uiState = uiState,
                onValueChange = {updateEvent ->
                    viewModel.updateStateSup(updateEvent)
                },
                onClick = {
                    viewModel.saveDataSup()
                    onNavigate()
                }
            ) 
        }
    }
}



@Composable
fun FormSupplier(
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    Column (

    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.id_sup,
            onValueChange = {
                onValueChange(supplierEvent.copy(id_sup = it))
            },
            label = { Text("ID Suplier")},
            isError = errorState.id_sup != null,
            placeholder = { Text("Masukan ID anda")}
        )
        Text(
            text = errorState.id_sup ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.namaSup,
            onValueChange = {
                onValueChange(supplierEvent.copy(namaSup = it))
            },
            label = { Text("Nama Suplier")},
            isError = errorState.namaSup != null,
            placeholder = { Text("Masukan Nama anda")}
        )
        Text(
            text = errorState.namaSup ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.kontakSup,
            onValueChange = {
                onValueChange(supplierEvent.copy(kontakSup = it))
            },
            label = { Text("Kontak Suplier")},
            isError = errorState.kontakSup != null,
            placeholder = { Text("Masukan Kontak anda")}
        )
        Text(
            text = errorState.kontakSup ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.alamatSup,
            onValueChange = {
                onValueChange(supplierEvent.copy(alamatSup = it))
            },
            label = { Text("Alamat Suplier")},
            isError = errorState.alamatSup != null,
            placeholder = { Text("Masukan Alamat anda")}
        )
        Text(
            text = errorState.alamatSup ?: "",
            color = Color.Red
        )
    }
}



@Composable
fun InsertBodySup(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: SupUIState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
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