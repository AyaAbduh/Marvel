package com.example.marvel.presentation.singlecharacter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.domain.repository.CharacterSpecificDetail
import com.example.marvel.presentation.viewmodel.CharacterDetailsViewModel
import com.example.marvel.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class CharacterDetailsActivity : AppCompatActivity() {

    private val characterDetailsViewModel = CharacterDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)

        val characterId: Int = intent.getIntExtra("id",1)

        getCharacterDetails(characterId.toString())
        setupRecyclerView(CharacterSpecificDetail.comics, characterId.toString())
        setupRecyclerView(CharacterSpecificDetail.events, characterId.toString())
        setupRecyclerView(CharacterSpecificDetail.series, characterId.toString())
        setupRecyclerView(CharacterSpecificDetail.stories, characterId.toString())
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

    private fun setupRecyclerView(requiredDetail: CharacterSpecificDetail, characterID: String) {
        val recyclerView: RecyclerView = when (requiredDetail) {
            CharacterSpecificDetail.series -> findViewById(R.id.seriesRecyclerView)
            CharacterSpecificDetail.comics -> findViewById(R.id.comicsRecyclerView)
            CharacterSpecificDetail.stories -> findViewById(R.id.storiesRecyclerView)
            CharacterSpecificDetail.events -> findViewById(R.id.eventsRecyclerView)
        }
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val adapter = CharacterDetailListAdapter()
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            characterDetailsViewModel.getSpecificDetail(characterID, requiredDetail).observe(this@CharacterDetailsActivity) { pagingData ->
                adapter.submitData(lifecycle, pagingData)
            }
        }
    }
}