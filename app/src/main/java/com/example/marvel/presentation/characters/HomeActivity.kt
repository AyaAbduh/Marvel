package com.example.marvel.presentation.characters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.marvel.data.Character
import com.example.marvel.presentation.characters.ui.theme.MarvelTheme
import com.example.marvel.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
     var homeViewModel  = HomeViewModel()
    val pagingItems: LazyPagingItems<Character>  = homeViewModel.items.collectAsLazyPagingItems()


    LazyColumn {
        items(pagingItems.itemCount) { index ->
            pagingItems[index]?.name?.let { Text(text = it, modifier = Modifier.padding(8.dp)) }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelTheme {
        Greeting("Android")
    }
}