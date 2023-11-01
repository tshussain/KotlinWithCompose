package com.example.kotlinwithcompose.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/* This constructor will new up our view model using injection as appropriate (in Factory) */
@Composable
fun UserProfilesScreen(myViewModel: UserProfileViewModel =
                            viewModel(factory=UserProfileViewModelFactory())) {
    val activeProfile by myViewModel.activeProfile.collectAsState()
    val allProfiles by myViewModel.allProfiles.collectAsState()

    Column {
        Button(
            onClick = {
                myViewModel.setName(
                    if (activeProfile == null) "x" else activeProfile.name + "x") }
        ) {
            Text(
                text = "Append to Name",
                fontSize = 16.sp
            )
        }
        if (activeProfile != null && activeProfile.name.isNotEmpty()) {
            Button(
                onClick = { myViewModel.increment() }
            ) {
                Text(
                    text = "Increment Counter",
                    fontSize = 16.sp
                )
            }

            if (activeProfile != null) {
                Button(
                    onClick = { myViewModel.deleteProfile(activeProfile.name) }
                ) {
                    Text(
                        text = "Delete Profile",
                        fontSize = 16.sp
                    )
                }
            }
            if (activeProfile != null) {
                Text(text = "Active Name: ${activeProfile.name}")
                Text(text = "Active Counter value ${activeProfile.counter}")
            }
        }
        if (allProfiles != null && allProfiles.isNotEmpty()) {
            Text(text = "Click a profile in the list to select it as the active profile")
            LazyColumn {
                items(allProfiles) { profile ->
                    Text(text = "Name: " + profile.name + " and counter " + profile.counter,
                        modifier = Modifier
                            .clickable { myViewModel.selectProfile(profile.name) }
                            .padding(16.dp))
                }
            }
        } else {
            Text(text="There are no saved user profiles")
        }
    }
}

/* Stateless */
@Composable
fun DisplayGivenList(idList: List<String>) {
    LazyColumn {
        items(idList) { id ->
            Text(text = "" + id)
        }
    }
}