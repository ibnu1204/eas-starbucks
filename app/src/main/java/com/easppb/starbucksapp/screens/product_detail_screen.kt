package com.easppb.starbucksapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.starbucksapp.R
import com.easppb.starbucksapp.common.AppIcon
import com.nameisjayant.starbucksapp.ui.theme.*


@Composable
fun ProductDetailScreen(
    navHostController: NavHostController
) {

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
            ProductHeader(navHostController)
            LazyColumn {
                item {
                    ShowProduct()
                }
                item {
                    ProductDescription()
                }
            }
        }
        AppButton(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter)
        )

    }

}

@Composable
fun ProductDescription(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
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
                    fontSize = 24.sp,
                    fontFamily = SemiboldFont
                ),
            )
            Row(
                modifier = Modifier.align(CenterVertically)
            ) {
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
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam at mi vitae augue feugiat scelerisque in a eros.",
            style = TextStyle(
                color = Gray500,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                fontFamily = MediumFont
            ),
        )
    }


}


@Composable
fun ShowProduct(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableStateOf(0) }
    Box(
        modifier = modifier
            .padding(top = 30.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(LightGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.image), contentDescription = "",
                modifier = Modifier.height(220.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .width(180.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            ) {
                AppIcon(icon = R.drawable.plus, background = DarkGreen) {
                    counter++
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "$counter",
                    style = TextStyle(
                        color = DarkGreen,
                        fontWeight = FontWeight.W400,
                        fontSize = 25.sp,
                        fontFamily = MediumFont
                    ),
                    modifier = Modifier.align(Center)
                )
                Spacer(modifier = Modifier.width(10.dp))
                AppIcon(
                    icon = R.drawable.minus, background = Gray, modifier = Modifier.align(
                        TopEnd
                    )
                ) {
                    if (counter > 0)
                        counter--
                }
            }
        }
    }

}

@Composable
fun AppButton(
    modifier: Modifier = Modifier
) {

    Button(
        onClick = {}, modifier = modifier.fillMaxWidth(),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(37.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkGreen
        ),
    ) {
        Text(
            text = stringResource(R.string.add_to_bag),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                fontFamily = MediumFont
            ),
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }

}


@Composable
fun ProductHeader(
    navHostController: NavHostController
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppIcon(icon = R.drawable.back){
            navHostController.navigateUp()
        }
        Text(
            text = "Product Detail", style = TextStyle(
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = SemiboldFont,
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.padding(vertical = 10.dp)
        )
        AppIcon(icon = R.drawable.love)
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductDetailScreenPreview() {
    val navController = rememberNavController()
    ProductDetailScreen(navHostController = navController)
}
