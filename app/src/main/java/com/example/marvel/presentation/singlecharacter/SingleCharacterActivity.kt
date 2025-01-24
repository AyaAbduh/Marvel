package com.example.marvel.presentation.singlecharacter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.presentation.characters.CustomAdapter
import com.example.marvel.presentation.viewmodel.HomeViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class SingleCharacterActivity : AppCompatActivity() {

    private var  homeViewModel: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)

        val characterId: Int = intent.getIntExtra("id",1)


        getCharacterDetails(characterId.toString())
        getComics(characterId.toString())
        getEvents(characterId.toString())
        getseries(characterId.toString())
        getStories(characterId.toString())
    }

    fun getCharacterDetails(characterId:String){
        homeViewModel.getCharacterDetails(characterId,"1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
        homeViewModel.getCharactersLiveData.observe(this){
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
        homeViewModel.getComics(characterId,"1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
        homeViewModel.getComicsLiveData.observe(this){
        val ComicsAdapter = it.data?.ComicsList?.let { it1 -> ComicsAdapter(it1) }
        val recyclerView: RecyclerView = findViewById(R.id.comicsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = ComicsAdapter
        }
    }

    private fun getEvents(characterId: String) {
        homeViewModel.getEvents(characterId,"1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
        homeViewModel.getEventsLiveData.observe(this) {
            if (!it.data?.eventList.isNullOrEmpty()) {
                val EventsAdapter = it.data?.eventList?.let { it1 -> EventsAdapter(it1) }
                val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
                recyclerView.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = EventsAdapter
            }else{
                val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
                recyclerView.visibility=View.INVISIBLE
                findViewById<TextView>(R.id.EventsTextView).visibility=View.INVISIBLE
            }

    }}

    private fun getseries(characterId: String) {
        homeViewModel.getSeries(characterId,"1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
        homeViewModel.getSeriesLiveData.observe(this){
            val SeriesAdapter = it.data?.seriesList?.let { it1 -> SeriesAdapter(it1) }
            val recyclerView: RecyclerView = findViewById(R.id.seriesRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = SeriesAdapter
    }}

    private fun getStories(characterId: String) {
        homeViewModel.getStories(characterId,"1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
        homeViewModel.getStoriesLiveData.observe(this){
            val StoriesAdapter = it.data?.storyList?.let { it1 -> StoriesAdapter(it1) }
            val recyclerView: RecyclerView = findViewById(R.id.storiesRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = StoriesAdapter
    }}






}










