@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
                    val result1: Boolean = searchThis("Joe", {str -> str.length >= 4})

                    val result2: Boolean = searchThis("Jane") {
                        str -> str.length >= 4
                    }
                    val result3: Boolean = searchThis("Joe", {it.length >= 4})

                    val result4: Boolean = searchThis("Jane") {
                        it.length >= 4
                    }
                    println("result1: $result1 + result2: $result2 result3: $result3 + result4: $result4")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text("My App") }) },
                        bottomBar = { BottomAppBar { Text("Copyright (c) 2023 CoolEntertainment, Inc.") } },
                        floatingActionButton = { FloatingActionButton(onClick = {}) { Text("Click Me")} }
                    ) {
                        Column(modifier = Modifier.padding(paddingValues = it)) {
                            var image = painterResource(R.drawable.penguin)
                            Image(
                                painter = image,
                                contentDescription = "Penguins having fun",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50.dp)),
                                contentScale = ContentScale.Crop

                            )
    
                            Row(modifier = Modifier.height(250.dp)) {
                                DisplayList()
                                ChangingList()
                                ListContent(onItemClick = {})

                            }
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

fun searchThis(name: String, query: (String) -> Boolean): Boolean {
    return query(name)
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
        MyForm()
        Card(
            onClick = { /* Do something */ },
            modifier = Modifier.size(width = 180.dp, height = 100.dp),
        ) {
            Box(Modifier.fillMaxSize()) {
                Text("Clickable", Modifier.align(Alignment.Center))
            }
        }
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

@Composable
fun MyForm() {
    var nameValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var ageValue by rememberSaveable { mutableStateOf("") }
    var loggedInStatus by rememberSaveable { mutableStateOf(false)}

    Column {
        if (!loggedInStatus) {
            TextField(
                value = nameValue,
                onValueChange = { nameValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your username (Must be non-empty)") }
            )
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(20.dp))
            TextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your password (Must be at least 8 characters long)") }
            )
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(20.dp))
            TextField(
                value = ageValue,
                onValueChange = { ageValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your age (Must be 18 or older)") }
            )

            if (!nameValue.isEmpty() && !passwordValue.isEmpty()
                && passwordValue.length >= 8 && !ageValue.isEmpty()
                && ageValue.toInt() >= 18
            ) {
                Button(onClick = { loggedInStatus = true }) {
                    Text("Signup")
                }
            }
        }
        else {
            Text("Welcome $nameValue. You are $ageValue years old.")
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
