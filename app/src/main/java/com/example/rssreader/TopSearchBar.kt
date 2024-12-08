package com.example.rssreader

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    sharedViewModel: SharedViewModel
) {
    val searchText by sharedViewModel.searchText.observeAsState("")

    TopAppBar(
        title = {
            TextField(
                value = searchText,
                onValueChange = { sharedViewModel.updateSearchText(it) },
                placeholder = { Text("Search...") },
                singleLine = true
            )
        }
    )
}