package com.example.marvel.presentation.characters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.data.Character
import com.example.marvel.presentation.characters.ui.theme.MarvelTheme
import com.example.marvel.presentation.singlecharacter.CharacterDetailsActivity
import com.example.marvel.presentation.singlecharacter.CharacterDetailssActivity
import com.example.marvel.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Marvel") },
                            navigationIcon = {
                                IconButton(onClick = { navigationClickAction() }) {
                                    Icon(painter = painterResource(id = R.drawable.backbtn), contentDescription = "Back")
                                }
                            },
                            actions = {
                                IconButton(onClick = {  onClickAction() }) {
                                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "Settings")
                                }
                            },
                        )
                    },
                    content = { paddingValues ->
                    Home(modifier = Modifier.padding( paddingValues ))
                    }
                )
            }
        }
    }

    private fun navigationClickAction() {
        println("navigation icon clicked!")

    }

    private fun onClickAction() {
        println("Settings icon clicked!")

    }
}

@Composable
fun Home( modifier: Modifier = Modifier) {
     var homeViewModel  = HomeViewModel()
    val pagingItems: LazyPagingItems<Character>  = homeViewModel.items.collectAsLazyPagingItems()


    LazyColumn {
        items(pagingItems.itemCount) { index ->

            Box{
                //Image View
                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            Glide.with(context)
                                .load(pagingItems[index]?.thumbnail?.path?.replace("http","https")+"."+pagingItems[index]?.thumbnail?.extension) // URL or resource for the image
                                .into(this)
                            scaleType = ImageView.ScaleType.FIT_XY  // Apply scale type to the ImageView
                            setOnClickListener {
                                onImageClick(context,pagingItems[index]?.id!!) // Trigger the provided lambda function
                            }

                        }
                    },
                    modifier = Modifier.fillMaxSize().height(200.dp), // Modify size of Image
                    )

                pagingItems[index]?.name?.let {
                    Text(text = it, modifier = Modifier.padding(top = 100.dp, start = 16.dp).background(Color.White), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }


            }



        }
    }

}

fun onImageClick(context: Context,id:Int) {

    val intent = Intent(context, CharacterDetailssActivity::class.java)
    intent.putExtra("id", id)

    context.startActivity(intent) // Start the activity

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelTheme {
        Home()
    }
}