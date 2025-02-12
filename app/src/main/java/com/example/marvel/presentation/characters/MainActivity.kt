package com.example.marvel.presentation.characters

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.presentation.search.SearchActivity
import com.example.marvel.presentation.singlecharacter.CharacterDetailsActivity
import com.example.marvel.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCharacters()
        setupSearchAction()
    }

    private fun setupSearchAction() {
        val searchImage: ImageView = findViewById(R.id.imageView)
        searchImage.setOnClickListener {
            val intent = Intent(searchImage.context, SearchActivity::class.java)
            searchImage.context.startActivity(intent)
        }
    }

    private fun getCharacters() {
        val customAdapter = ItemAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

//        lifecycleScope.launch {
//            homeViewModel.items.observe(this@MainActivity) { pagingData ->
//                customAdapter.submitData(lifecycle, pagingData)
//            }
//        }
    }
}