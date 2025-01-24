package com.example.marvel.presentation.singlecharacter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.data.Series

class SeriesAdapter(private val dataSet: List<Series>) :
    RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView)
            imageView=view.findViewById<ImageView>(R.id.CharacterImageView)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.textView.text = dataSet.get(position).title

        Glide.with(viewHolder.imageView.context)
            .load(dataSet.get(position).thumbnail?.path?.replace("http","https")+"."+dataSet.get(position).thumbnail?.extension)
            .into(viewHolder.imageView)

        viewHolder.imageView.setOnClickListener {
            val intent = Intent(viewHolder.textView.context, SingleCharacterActivity::class.java)
            intent.putExtra("id", dataSet.get(position).id)
            viewHolder.textView.context.startActivity(intent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
