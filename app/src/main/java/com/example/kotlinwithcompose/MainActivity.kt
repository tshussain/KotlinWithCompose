@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinwithcompose.ui.theme.AppTheme
import androidx.compose.ui.text.TextStyle

//Vacuous change for testing CI/CD2
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { TopAppBar(title = { Text("My App") }) },
                        bottomBar = { BottomAppBar { Text("Copyright (c) 2023 CoolEntertainment, Inc.") } },
                        floatingActionButton = { FloatingActionButton(onClick = {}) { Text("Click Me")} }
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            var image = painterResource(R.drawable.penguin)
                            Image(
                                painter = image,
                                contentDescription = "Penguins having fun",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50.dp)),
                                contentScale = ContentScale.Crop

                            )

                            Greeting()

                            // Examples of functions as parameters
                            var lambda = { a: Int -> a + 2 }

                            higherFunc(::existingFunction)
                            higherFunc(lambda)
                            higherFunc(funcParam = { a: Int -> a + 3 })
                            higherFunc({ a: Int -> a + 4 })
                        }
                    }
                }
            }
        }
    }
}

fun existingFunction(a:Int) : Int {
    return a+1
}

fun higherFunc(funcParam: (Int) -> Int) {
    println("Result is:" + funcParam(2))
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text("Welcome to my app")
        CountWithButton()
        TextField(
            value = name,
            onValueChange = { name = it },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            label = { Text(text = "Please enter your name")}
        )
        Card(
            onClick = { /* Do something */ },
            modifier = Modifier.size(width = 180.dp, height = 100.dp),
        ) {
            Box(Modifier.fillMaxSize()) {
                Text("Clickable", Modifier.align(Alignment.Center))
            }
        }
        Text("Welcome to my app1")
        Text("Welcome to my app2")
        Text("Welcome to my app3")
        Text("Welcome to my app4")
        Text("Welcome to my app5")
        Text("Welcome to my app6")
        Text("Welcome to my app7")
        Text("Welcome to my app8")
        Text("Welcome to my app9")
        Text("Welcome to my app1")
        Text("Welcome to my app2")
        Text("Welcome to my app3")
        Text("Welcome to my app4")
        Text("Welcome to my app5")
        Text("Welcome to my app6")
        Text("Welcome to my app7")
        Text("Welcome to my app8")
        Text("Welcome to my app9")
        Text("Welcome to my app1")
        Text("Welcome to my app2")
        Text("Welcome to my app3")
        Text("Welcome to my app4")
        Text("Welcome to my app5")
        Text("Welcome to my app6")
        Text("Welcome to my app7")
        Text("Welcome to my app8")
        Text("Welcome to my app9")


    }
}



@Composable
fun CountWithButton() {
    Column(modifier = Modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        Text("You've had ${count} glasses.")
        ElevatedButton(onClick = { count++ },
            Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting()
    }
}