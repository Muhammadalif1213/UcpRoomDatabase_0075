package com.example.ucp2_075.ui.customWidget

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2_075.R

@Composable
fun HomeTopBar(
){
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(bottomEnd = 60.dp))
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF42A5F5))
                .fillMaxHeight()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()){

                Row {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "DJI STORE",
                            color = Color.White,
                            style = TextStyle(fontSize = 30.sp), fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "SZ DJI Technology Co., Ltd",
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp)
                        )

                    }
                    Image(
                        painter = painterResource(id = R.drawable.drone),
                        contentDescription = "DJI Logo",
                        modifier = Modifier.size(100.dp)
                    )

                }

            }

        }

    }
}