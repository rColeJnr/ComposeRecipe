package com.rick.composerecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.rick.composerecipe.ui.theme.ComposeRecipeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyMeal{
                val list: List<String> = List(5){"here you can define $it"}
                MyScreenContent(list)
            }
        }
    }
}

@Composable
fun HappyMeal(content: @Composable () -> Unit) {
    ComposeRecipeTheme() {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) {"Hello Android #$it"}){
    val counterState = remember { mutableStateOf(0)}

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = {
                counterState.value = it
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names) { ass -> // i assume that ass is a parameter of items, as ass in asses
            Greeting(name = ass)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String){
    // by means that another class wil handle the setting and getting
    // of these values, as if it's already sen during runtime, when accessed again
    // it will be given the value in memory
    var isSelected by remember { mutableStateOf(false) }
    val backGroundColor by animateColorAsState(targetValue = if (isSelected) Color.Red else Color.Transparent)

    Text (
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backGroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit){
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.Red
        )
    ) {
        Text("I've been clicked $count times")
    }
}