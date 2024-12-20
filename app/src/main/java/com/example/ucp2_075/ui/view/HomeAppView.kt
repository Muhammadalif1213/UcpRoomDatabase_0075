package com.example.ucp2_075.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucp2_075.ui.customWidget.HomeTopBar


@Composable
fun HomeAppView(
    modifier: Modifier = Modifier,
    onNavigateAddSup: () -> Unit
){
    Scaffold (
        modifier = modifier,
        topBar ={
            HomeTopBar()
        }
    ){ innerPadding ->

        BodyHome(
            modifier = Modifier.padding(innerPadding),
            onBrgListClick ={

            },
            onAddSupClick = {
                onNavigateAddSup()
            }
        )
    }
}


@Composable
fun BodyHome(
    modifier: Modifier = Modifier,
    onBrgListClick: () -> Unit,
    onAddSupClick: () -> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth().padding(16.dp)
    ){
        Row (
        ){
            CardMenu(
                namaMenu = "List Produk",
                onClick = onBrgListClick
            )
            CardMenu(
                namaMenu = "Add Product"
            )
        }
        Row(
        ) {
            CardMenu(
                namaMenu = "List Supplier"
            )
            CardMenu(
                namaMenu = "AddSupplier",
                onClick = onAddSupClick
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    namaMenu: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(180.dp)
            .height(230.dp)
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF42A5F5)) // Cyan color
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    tint = Color.White,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = namaMenu,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}