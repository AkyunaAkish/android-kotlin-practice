package com.example.rssreader

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.rssreader.ui.theme.RSSReaderTheme
import androidx.lifecycle.ViewModelProvider

class CounterActivity : ComponentActivity() {
    private val sharedViewModel by lazy {
        ViewModelProvider(AppViewModelStore).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RSSReaderTheme {
                var counter by remember { mutableStateOf(0) }
                val context = LocalContext.current
                val searchText by sharedViewModel.searchText.observeAsState("")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopSearchBar(sharedViewModel) },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                label = { Text("Home") },
                                selected = false,
                                onClick = {
                                    // Navigate back to MainActivity
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent)
                                    finish()  // Close current activity
                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Add, contentDescription = "Counter") },
                                label = { Text("Counter") },
                                selected = true,
                                onClick = { /* Already on counter */ }
                            )
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Text(
                            text = "Current search: $searchText",
                            modifier = Modifier.padding(16.dp)
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = "Counter: $counter",
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                Row(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(onClick = { counter-- }) {
                                        Text("-")
                                    }

                                    Button(onClick = { counter++ }) {
                                        Text("+")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}