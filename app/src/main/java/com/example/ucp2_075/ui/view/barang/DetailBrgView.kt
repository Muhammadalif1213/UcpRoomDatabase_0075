package com.example.ucp2_075.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_075.data.entity.Barang
import com.example.ucp2_075.ui.customWidget.TopAppBar
import com.example.ucp2_075.ui.viewmodel.DetailBrgUiState
import com.example.ucp2_075.ui.viewmodel.DetailBrgViewModel
import com.example.ucp2_075.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_075.ui.viewmodel.toBarangEntity


@Composable
fun DetailBrgView (
    modifier: Modifier = Modifier,
    viewModel: DetailBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (Int) -> Unit = { },
    onDeleteClick: () -> Unit = {}
) {
    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "Detail Barang",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailBrgUIstate.value.detailBarangUiEvent.idBarang)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Barang"
                )
            }
        }
    ){ innerPadding ->
        val detailBrgUiState by viewModel.detailBrgUIstate.collectAsState()

        BodyDetailBrg(
            modifier = Modifier.padding(innerPadding),
            detailBrgUiState = detailBrgUiState,
            onDeleteClick = {
                viewModel.deleteBrg()
                onDeleteClick()
            }
        )
    }
}


@Composable
fun BodyDetailBrg(
    modifier: Modifier = Modifier,
    detailBrgUiState: DetailBrgUiState,
    onDeleteClick: () -> Unit = {}
){
    var deleteConfifrmationRequired by rememberSaveable { mutableStateOf(false) }
    when{
        detailBrgUiState.isLoading ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator() //Tampilan loading
            }
        }

        detailBrgUiState.isBrgUiEventNotEmpty -> {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                ItemDetailBrg(
                    barang = detailBrgUiState.detailBarangUiEvent.toBarangEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfifrmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }

                if (deleteConfifrmationRequired){
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfifrmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {deleteConfifrmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailBrgUiState.isBrgUiEventEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBrg(judul = "ID_Barang", isinya = barang.idBarang.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Nama Barang", isinya = barang.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Deskripsi", isinya = barang.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Harga", isinya = barang.harga)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Stok", isinya = barang.stok.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Supplier", isinya = barang.nama_sup)
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }
}


@Composable
fun ComponentDetailBrg(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column (
        modifier = modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /* Do Nothing */},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data? ") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
