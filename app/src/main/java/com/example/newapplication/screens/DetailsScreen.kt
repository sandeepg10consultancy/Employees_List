package com.example.newapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailsScreen(modifier: Modifier= Modifier,name: String?) {
    Column(
        modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (name != null){
            Text(text = name,
                modifier.fillMaxWidth()
                    .wrapContentHeight(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }

}