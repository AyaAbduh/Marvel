package com.example.marvel.presentation.characters

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.presentation.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private var  homeViewModel: HomeViewModel = HomeViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            getCharacters()
    }



 fun getCharacters(){
     homeViewModel.getCharacters("1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3")
     homeViewModel.getCharactersLiveData.observe(this){

         val customAdapter = it.data?.characterList?.let { it1 -> CustomAdapter(it1) }
         val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
         recyclerView.layoutManager = LinearLayoutManager(this)
         recyclerView.adapter = customAdapter
         //handling search
         val searchEditText = findViewById<EditText>(R.id.editTextText)
         searchEditText.addTextChangedListener(object:TextWatcher{
             override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
             }

             override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filteredList=it.data?.characterList?.filter {
                    it.name?.contains(s.toString(), ignoreCase = true)!!
                }
                 val customAdapter = CustomAdapter(filteredList!!)
                 recyclerView.adapter = customAdapter
             }

             override fun afterTextChanged(p0: Editable?) {
             }

         })
     }

 }
}