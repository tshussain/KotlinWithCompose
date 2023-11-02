package com.example.kotlinwithcompose

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

/* This is a sample Composable that would be part of a
     different project/app which would launch this app via intents and deep links
 */
@Composable
fun LauncherFor2ndApp(modifier: Modifier = Modifier) {
    // Fetching the Local Context
    val localContext = LocalContext.current
    var resultText by remember { mutableStateOf("") }

    val activityLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
        if (result.resultCode == Activity.RESULT_OK) {
            // Handle the result data here
            val data = result.data
            println("Received result: $data")
            resultText = data?.getStringExtra("resultData") ?: ""
        }
    }
    Column {
        Text(
            text = "Hello!.  Click me to go to second activity in the same app",
            modifier = modifier
                .clickable {
            // Commented out since second activity doesn't exist in this project.
//                    localContext.startActivity(
//                        Intent(localContext, SecondActivity::class.java)
//                    )
                }
        )
        Text(
            text = "Hello!.  Click me to go to a different app",
            modifier = modifier
                .clickable {
                    localContext.startActivity(
                        Intent(Intent.ACTION_MAIN)
                            .setComponent(
                                ComponentName(
                                    "com.example.kotlinwithcompose", //The package name of the app to which intent is to be sent
                                    "com.example.kotlinwithcompose.MainActivity"
                                )
                            )
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
        )
        Text(
            text = "Hello!.  Click me to go to a different app and get a result",
            modifier = modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_MAIN)
                        .setComponent(
                            ComponentName(
                                "com.example.kotlinwithcompose", //The package name of the app to which intent is to be sent
                                "com.example.kotlinwithcompose.MainActivity"
                            ))
                    activityLauncher.launch(intent)
                }
        )
        Text(
            text = "Hello!.  Click me to use a deep link to a screen/location in another app",
            modifier = modifier
                .clickable {
                    localContext.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("example://compose.deeplink"))
                    )
                }
        )
        val id = "Joe"
        Text(
            text = "Hello!.  Click me - deeplink with parameters",
            modifier = modifier
                .clickable {
                    localContext.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("example://compose.deeplink2/?id=$id"))
                    )
                }
        )
        Text(
            text = "Hello!.  Click me - deeplink with parameters and results",
            modifier = modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("example://compose.deeplink2/?id=$id"))
                    activityLauncher.launch(intent)
                }
        )
        if (resultText.isNotEmpty()) {
            Text("The result is $resultText")
        } else {
            Text("No results yet")
        }
    }
}