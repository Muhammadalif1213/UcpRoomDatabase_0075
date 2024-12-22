package com.example.ucp2_075.ui.view.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.ucp2_075.data.entity.Barang
import com.example.ucp2_075.data.entity.Supplier
import com.example.ucp2_075.ui.viewmodel.ListBrgUIstate
import com.example.ucp2_075.ui.viewmodel.ListBrgViewModel
import com.example.ucp2_075.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_075.ui.customWidget.TopAppBar
import kotlinx.coroutines.launch

@Composable
fun ListBrgView(
    onBack: () ->Unit,
    viewModel: ListBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = { },
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "List Barang",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) {
        innerPadding ->
        val listBrgUIstate by viewModel.listBrgUiState.collectAsState()

        BodyListBrgView(
            listBrgUIstate = listBrgUIstate,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )

    }
}



@Composable
fun BodyListBrgView(
    listBrgUIstate: ListBrgUIstate,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        listBrgUIstate.isLoading ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        listBrgUIstate.isError ->{
            LaunchedEffect(listBrgUIstate.errorMessage) {
                listBrgUIstate.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        listBrgUIstate.listBrg.isEmpty() ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data Barang",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListBarang(
                listBrg = listBrgUIstate.listBrg,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}


@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBrg(
                    brg = brg,
                    onClick = {onClick(brg.id_barang)}
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF42A5F5)) // Cyan color
                .fillMaxHeight()
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth().fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "", modifier = Modifier.size(62.dp))
                Spacer(modifier = Modifier.padding(4.dp))
                Column (
                    modifier = Modifier.padding(8.dp)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = brg.nama,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Filled.Info,
                            contentDescription = "")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = brg.deskripsi,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Filled.Person,
                            contentDescription = "")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = brg.nama_sup,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

    }
}