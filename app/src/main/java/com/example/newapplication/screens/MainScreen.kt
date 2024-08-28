package com.example.newapplication.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newapplication.R
import com.example.newapplication.model.Data

@Composable
fun MainScreen(modifier: Modifier = Modifier,employeesList: List<Data>, navController: NavHostController) {
    Column(modifier.fillMaxSize()){
        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        SearchView(state = textState,placeholder = "Search...", modifier = Modifier,navController)
        val searchedText = textState.value.text
        LazyColumn(modifier.padding(bottom = 25.dp)){
            items(items = employeesList.filter {
                it.employee_name.contains(searchedText, ignoreCase = true)
            }, key = {it.id}){item ->
                EmployeeItem(
                    itemAge = item.employee_age,
                    itemName = item.employee_name,
                    itemSalary = item.employee_salary,
                    itemId = item.id,
                    itemImage = item.profile_image,
                    navController = navController
                )

            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeItem(
    itemName: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    itemAge: Int,
    itemSalary: Int,
    itemId: Int,
    itemImage: String
) {
    Card(
        modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("DetailsScreen?name=$itemName&age=$itemAge&salary=$itemSalary&id=$itemId&image=$itemImage")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        )){
        Row(
            modifier
                .padding(6.dp)
                .wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(painter = painterResource(id = R.drawable.people), contentDescription = itemName )
            //AsyncImage(model = R.drawable.people, contentDescription = itemName)
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = itemName,
                modifier
                    .fillMaxSize()
                    .basicMarquee(),
                fontSize = 20.sp)
            

        }

    }
}


@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    placeholder: String,
    modifier: Modifier,
    navController: NavHostController
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        TextField(value = state.value, onValueChange = {value ->
            state.value = value
        },
            modifier
                .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(2.dp, Color.Blue, RoundedCornerShape(30.dp)),
            placeholder = {
                Text(text = placeholder)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Red
            ),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black)
        )
        Button(onClick = {
            navController.navigate(route = "NewUserScreen")
        },
            modifier = Modifier.padding(top = 20.dp)) {
            Text(text = "Create")
        }
        
    }

}