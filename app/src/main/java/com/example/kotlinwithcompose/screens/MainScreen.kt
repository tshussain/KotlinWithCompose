@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import com.example.kotlinwithcompose.LocalList

import com.example.kotlinwithcompose.R
import com.example.kotlinwithcompose.composables.ChangingList
import com.example.kotlinwithcompose.composables.DisplayList
import com.example.kotlinwithcompose.composables.Greeting
import com.example.kotlinwithcompose.composables.ListContent
import com.example.kotlinwithcompose.layout.MainLayout
import com.example.kotlinwithcompose.model.MyData
import com.example.kotlinwithcompose.model.MySimpleSavedScreen
import com.example.kotlinwithcompose.model.MySimpleScreen
import com.example.kotlinwithcompose.model.MySimplerScreen
import com.example.kotlinwithcompose.model.MySimplestScreen
import com.example.kotlinwithcompose.model.UserProfilesScreen

@Composable
fun MainScreen() {
    val navController = LocalNavController.current
    val mainList = LocalList.current
    MainLayout(screenTitle = "Home") {

        AuthLoginScreen()
//        MySimplestScreen()
//        MySimplerScreen()
//        MySimpleScreen()
        MySimpleSavedScreen()
        UserProfilesScreen()

//        Button(onClick = { mainList.add(MyData("Joe", mainList.size))
//        }) {
//            Text("Add")
//        }

        LazyColumn {
            itemsIndexed(mainList) { index, item ->
                Text(text = "Indexx # ${index}: ${item.name} is ${item.age} years old",
                    modifier = Modifier
                        .clickable { navController.navigate(Routes.Details.go(index)) }
                        .padding(16.dp)
                )
                IconButton(onClick = { mainList.remove(item) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }




        Button(onClick = { navController.navigate(Routes.About.go("Wichita"))
        }) {
            Text("About Us")
        }
        Button(onClick = { navController.navigate(Routes.Contact.go("Akira","Japan")) }) {
            Text("Contact Us")
        }
        Button(onClick = { navController.navigate(Routes.Register.Login.go("Travis"))
        }) {
            Text("Login")
        }
        Button(onClick = { navController.navigate(Routes.Register.Signup.route)
        }) {
            Text("Signup")
        }
        if (navController.previousBackStackEntry != null) {
            Button(onClick = { navController.navigateUp() }) {
                Text("Back")
            }
        }

        var image = painterResource(R.drawable.penguin)
        Image(
            painter = image,
            contentDescription = "Penguins having fun",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop

        )

        Row(modifier = Modifier
            .height(250.dp)
            .width(400.dp)) {
            DisplayList()
            ChangingList()
            ListContent(onItemClick = {})

        }
        Greeting()
//
//            // Examples of functions as parameters
//            var lambda = { a: Int -> a + 2 }
//
//            higherFunc(::existingFunction)
//            higherFunc(lambda)
//            higherFunc(funcParam = { a: Int -> a + 3 })
//            higherFunc({ a: Int -> a + 4 })
//
//            val result1: Boolean = searchThis("Joe", {str -> str.length >= 4})
//
//            val result2: Boolean = searchThis("Jane") {
//                    str -> str.length >= 4
//            }
//            val result3: Boolean = searchThis("Joe", {it.length >= 4})
//
//            val result4: Boolean = searchThis("Jane") {
//                it.length >= 4
//            }
//            println("result1: $result1 + result2: $result2 result3: $result3 + result4: $result4")

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



