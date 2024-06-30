package com.easppb.starbucksapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.easppb.starbucksapp.common.AppIcon
import com.nameisjayant.starbucksapp.R
import com.easppb.starbucksapp.navigation.DETAIL_SCREEN
import com.nameisjayant.starbucksapp.ui.theme.*


@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    var search by remember { mutableStateOf("") }
    val menuList = listOf("All", "Frappuccino", "Breakfast", "Coffee", "Tea")
    var selected by remember { mutableStateOf("All") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Header()
            LazyColumn {
                item {
                    Box() {
                        CustomSearchBar(value = search, onValueChange = { search = it })
                    }
                }
                item {
                    LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
                        items(menuList) {
                            CustomFilterChip(title = it, selected = it == selected) { data ->
                                selected = data

                            }
                        }
                    }
                }
                item {
                    BestSeller(navHostController)
                }
                item {
                    Popular(navHostController)
                }
            }
        }
    }
}

@Composable
fun BestSeller(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Best Seller",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    fontFamily = MediumFont
                ),
            )
            Text(
                text = "See All",
                style = TextStyle(
                    color = DarkGreen,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    fontFamily = MediumFont
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(5) {
                ItemEachRow{
                    navHostController.navigate(DETAIL_SCREEN)
                }
            }
        }
    }

}

@Composable
fun Popular(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Popular",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    fontFamily = MediumFont
                ),
            )
            Text(
                text = "See All",
                style = TextStyle(
                    color = DarkGreen,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    fontFamily = MediumFont
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(5) {
                ItemEachRow{
                    navHostController.navigate(DETAIL_SCREEN)
                }
            }
        }
    }

}

@Composable
fun ItemEachRow(
    onClick:()->Unit
) {

    var selected by remember { mutableStateOf(false) }


    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .clickable { onClick() }
            .width(220.dp)
            .padding(end = 10.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 14.dp,
                            bottomEnd = 14.dp
                        )
                    )
                    .background(LightGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image), contentDescription = "",
                    modifier = Modifier
                        .height(180.dp),
                )
            }
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Red Velvet",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            fontFamily = MediumFont
                        ),
                    )
                    IconButton(onClick = {
                        selected = !selected
                    }, modifier = Modifier.size(24.dp).align(CenterVertically)) {
                        Icon(
                            painter = painterResource(id = R.drawable.love),
                            contentDescription = "",
                            tint = if (selected) Red else Color.Unspecified
                        )
                    }
                }
                Text(
                    text = "Rp45.000",
                    style = TextStyle(
                        color = DarkGreen,
                        fontWeight = FontWeight.W400,
                        fontSize = 25.sp,
                        fontFamily = SemiboldFont
                    ),
                )
            }


        }
    }

}

@Composable
fun CustomFilterChip(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,

    ) {

    TextButton(
        onClick = { onValueChange(title) },
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) DarkGreen else Color.White
        ),
        modifier = modifier.padding(end = 12.dp)
    ) {
        Text(
            text = title, style = TextStyle(
                color = if (selected) Color.White else TextColor,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                fontFamily = RegularFont
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
        )
    }

}

@Composable
fun CustomSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LightGray)
    ) {
        TextField(
            value = value, onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = "Search coffee or tea", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.W400,
                        color = DarkGray
                    )
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {}
        )
    }

}

@Composable
fun Header() {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Let’s order and enjoy\nyour order!", style = TextStyle(
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = SemiboldFont,
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.padding(vertical = 40.dp)
        )
        AppIcon(
            icon = R.drawable.bag,
            modifier = Modifier.padding(vertical = 40.dp)
        )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navHostController = navController)
}