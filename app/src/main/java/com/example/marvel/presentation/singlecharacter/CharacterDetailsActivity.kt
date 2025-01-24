package com.example.marvel.presentation.singlecharacter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.presentation.viewmodel.CharacterDetailsViewModel
import com.example.marvel.presentation.viewmodel.HomeViewModel

class CharacterDetailsActivity : AppCompatActivity() {

    private val characterDetailsViewModel = CharacterDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)

        val characterId: Int = intent.getIntExtra("id",1)

        getCharacterDetails(characterId.toString())
        getComics(characterId.toString())
        getEvents(characterId.toString())
        getSeries(characterId.toString())
        getStories(characterId.toString())
    }

    private fun getCharacterDetails(characterId:String){
        characterDetailsViewModel.getCharacterDetails(characterId)
        characterDetailsViewModel.getCharactersLiveData.observe(this){
           val characterImageView = findViewById<ImageView>(R.id.CharacterImageView)
            val nameTextView =findViewById<TextView>(R.id.nameTextView)
            val descTextView =findViewById<TextView>(R.id.descTextView)
            val descTextViewTitle =findViewById<TextView>(R.id.descTextViewTitle)

            Glide.with(this)
                .load(it.data?.characterList?.get(0)?.thumbnail?.path?.replace("http","https")+"."+it.data?.characterList?.get(0)?.thumbnail?.extension)
                .into(characterImageView)

            nameTextView.text=it.data?.characterList?.get(0)?.name
            if(!it.data?.characterList?.get(0)?.description.isNullOrEmpty()){
                descTextView.text=it.data?.characterList?.get(0)?.description
            }else{
                descTextViewTitle.visibility=View.INVISIBLE
            }
        }
    }

    private fun getComics(characterId: String) {
        characterDetailsViewModel.getComics(characterId)
        characterDetailsViewModel.getComicsLiveData.observe(this){
        val comicsAdapter = it.data?.dataList?.let { it1 -> ItemDetailsCellAdapter(it1) }
        val recyclerView: RecyclerView = findViewById(R.id.comicsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = comicsAdapter
        }
    }

    private fun getEvents(characterId: String) {
        characterDetailsViewModel.getEvents(characterId)
        characterDetailsViewModel.getEventsLiveData.observe(this) {
            if (!it.data?.dataList.isNullOrEmpty()) {
                val eventsAdapter = it.data?.dataList?.let { it1 -> ItemDetailsCellAdapter(it1) }
                val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
                recyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = eventsAdapter
            } else {
                val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
                recyclerView.visibility=View.INVISIBLE
                findViewById<TextView>(R.id.EventsTextView).visibility=View.INVISIBLE
            }
    }}

    private fun getSeries(characterId: String) {
        characterDetailsViewModel.getSeries(characterId)
        characterDetailsViewModel.getSeriesLiveData.observe(this){
            val seriesAdapter = it.data?.dataList?.let { it1 -> ItemDetailsCellAdapter(it1) }
            val recyclerView: RecyclerView = findViewById(R.id.seriesRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = seriesAdapter
    }}

    private fun getStories(characterId: String) {
        characterDetailsViewModel.getStories(characterId)
        characterDetailsViewModel.getStoriesLiveData.observe(this){
            val storiesAdapter = it.data?.dataList?.let { it1 -> ItemDetailsCellAdapter(it1) }
            val recyclerView: RecyclerView = findViewById(R.id.storiesRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = storiesAdapter
    }}
}