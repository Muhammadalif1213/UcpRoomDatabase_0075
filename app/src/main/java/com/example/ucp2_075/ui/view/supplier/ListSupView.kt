package com.example.ucp2_075.ui.view.supplier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_075.data.entity.Supplier
import com.example.ucp2_075.ui.customWidget.TopAppBar
import com.example.ucp2_075.ui.viewmodel.ListSupViewModel
import com.example.ucp2_075.ui.viewmodel.ListUiState
import com.example.ucp2_075.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@Composable
fun ListSupView(
    onBack: () ->Unit,
    viewModel: ListSupViewModel = viewModel(factory = PenyediaViewModel.Factory),
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "List Supplier",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
    ) {
        innerPadding ->
        val listUiState by viewModel.listUiState.collectAsState()

        BodyListSupView(
            listUiState = listUiState,
            onClick = {},
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyListSupView(
    listUiState: ListUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar state
    when {
        listUiState.isLoading -> {
            //Menampilkan Indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        listUiState.isError -> {
            //Menampilkan pesan error
            LaunchedEffect(listUiState.errorMessage) {
                listUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        listUiState.listSup.isEmpty() -> {
            //menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data Supplier",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            //menampilkan daftar Supplier
            ListSupplier(
                listSup = listUiState.listSup,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupplier(
    listSup: List<Supplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
){
    LazyColumn (
        modifier = modifier
    ){
        items(
            items = listSup,
            itemContent = { sup ->
                CardSup(
                    sup = sup,
                    onClick = {onClick(sup.idSup.toString())}
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSup(
    sup: Supplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .padding(start = 4.dp)
        ){
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF42A5F5)) // Cyan color
                    .fillMaxHeight()
            ){
                Icon(imageVector = Icons.Filled.Person,
                    contentDescription = "", modifier = Modifier.size(95.dp))
            }
        }
        Card (
            onClick = onClick,
            modifier = modifier
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF42A5F5)) // Cyan color
                    .fillMaxHeight()
            ){
                Column (
                    modifier = Modifier.padding(8.dp)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = sup.namaSup,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Filled.Call,
                            contentDescription = "")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = sup.kontakSup,
                            fontSize = 16.sp
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Filled.Home,
                            contentDescription = "")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = sup.alamatSup,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

}