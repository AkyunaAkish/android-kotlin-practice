package com.example.rssreader


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rssreader.ui.theme.RSSReaderTheme
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    private val sharedViewModel by lazy {
        ViewModelProvider(AppViewModelStore).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val rssItems = listOf(
            RSSItem(
                "Welcome to my blog1", "Lorem ipsum1", RSSType.TEXT
            ),
            RSSItem(
                "Welcome to my blog2", "Lorem ipsum2", RSSType.IMAGE
            ),
            RSSItem(
                "Welcome to my blog3", "Lorem ipsum3", RSSType.TEXT
            ),
            RSSItem(
                "Welcome to my blog4", "Lorem ipsum4", RSSType.VIDEO
            ),
        )

        setContent {
            RSSReaderTheme {
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
                                selected = true,
                                onClick = { /* Already on home */ }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Add, contentDescription = "Counter") },
                                label = { Text("Counter") },
                                selected = false,
                                onClick = {
                                    val intent = Intent(context, CounterActivity::class.java)
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(
                            text = "Current search: $searchText",
                            modifier = Modifier.padding(16.dp)
                        )
                        LazyColumn(
                            modifier = Modifier.padding(innerPadding),
                            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                        ) {
                            items(rssItems) { rssItem ->
                                when (rssItem.type) {
                                    RSSType.TEXT -> RSSItemText(rssItem)
                                    RSSType.VIDEO -> RSSItemVideo(rssItem)
                                    RSSType.IMAGE -> RSSItemImage(rssItem)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RSSItemText(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = rssItem.title,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun RSSItemVideo(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Click below to play video",
            modifier = Modifier.padding(12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_airplay_24),
            contentDescription = "Video of RSS item titled: ${rssItem.title}",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
    }
}

@Composable
fun RSSItemImage(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Check out this image",
            modifier = Modifier.padding(12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.adb_img_24),
            contentDescription = "Image of RSS item titled: ${rssItem.title}",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
    }
}