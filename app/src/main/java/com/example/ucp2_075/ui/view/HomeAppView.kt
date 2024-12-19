package com.example.ucp2_075.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucp2_075.ui.customWidget.HomeTopBar

@Preview(showBackground = true)
@Composable
fun HomeAppView(
    modifier: Modifier = Modifier
){
    Scaffold (
        modifier = modifier,
        topBar ={
            HomeTopBar()
        }
    ){ innerPadding ->

        CardMenu(
            modifier = Modifier.padding(innerPadding)
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
  Card (
      onClick = onClick,
      modifier = modifier
          .width(300.dp)
          .height(400.dp)
          .padding(8.dp),

  ){
      Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
              .padding(16.dp)
              .fillMaxWidth()
              .fillMaxHeight()

      ) {
          Icon(imageVector = Icons.Filled.Menu,
              contentDescription = "")
          Spacer(modifier = Modifier.padding(8.dp))
          Text(
              text = "List Produk"
          )
      }
  }
}