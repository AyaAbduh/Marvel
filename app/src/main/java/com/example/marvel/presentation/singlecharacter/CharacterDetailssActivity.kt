package com.example.marvel.presentation.singlecharacter

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.marvel.data.Character
import com.example.marvel.presentation.characters.onImageClick
import com.example.marvel.presentation.singlecharacter.ui.theme.MarvelTheme
import com.example.marvel.presentation.viewmodel.CharacterDetailsViewModel

class CharacterDetailssActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val characterId: Int = intent.getIntExtra("id",1)

                    CharacterDetails(
                        characterId = characterId,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterDetails(characterId: Int, modifier: Modifier = Modifier) {

    var characterDetailsViewModel= CharacterDetailsViewModel()
    characterDetailsViewModel.getCharacterDetails(characterId.toString())
    val liveDataValue by characterDetailsViewModel.getCharactersLiveData.observeAsState(null)
   //liveDataValue.value?.data?.characterList?.get(0)?.thumbnail
    if(!liveDataValue?.data?.characterList.isNullOrEmpty()) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth().background(Color.Blue)) {
                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            Glide.with(context)
                                .load(
                                    liveDataValue?.data?.characterList?.get(0)?.thumbnail?.path?.replace(
                                        "http",
                                        "https"
                                    ) + "." + liveDataValue?.data?.characterList?.get(0)?.thumbnail?.extension
                                ) // URL or resource for the image
                                .into(this)
                            scaleType =
                                ImageView.ScaleType.FIT_XY  // Apply scale type to the ImageView

                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(200.dp), // Modify size of Image
                )

            }
            Row(Modifier.fillMaxWidth().padding(16.dp)) {
                 Text(
                     text = liveDataValue?.data?.characterList?.get(0)?.name!!,
                 )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MarvelTheme {
        CharacterDetails(1)
    }
}