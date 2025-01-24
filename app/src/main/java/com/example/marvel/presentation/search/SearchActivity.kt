package com.example.marvel.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.presentation.characters.CustomAdapter
import com.example.marvel.presentation.characters.ItemAdapter
import com.example.marvel.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {
    private val searchViewModel = SearchViewModel()
    private val customAdapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupRecyclerView()
        handleSearch()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }

    private fun handleSearch() {
        val searchEditText = findViewById<EditText>(R.id.editTextText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lifecycleScope.launch {
                    searchViewModel.searchFor(s.toString()).observe(this@SearchActivity) { pagingData ->
                        customAdapter.submitData(lifecycle, pagingData)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}