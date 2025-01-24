package com.example.marvel.presentation.singlecharacter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.data.ItemDetailsCellModel

class CharacterDetailListAdapter(): PagingDataAdapter<ItemDetailsCellModel, CharacterDetailListAdapter.CharacterDetailListViewHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return CharacterDetailListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterDetailListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CharacterDetailListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById<TextView>(R.id.textView)
        private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.CharacterImageView)

        fun bind(item: ItemDetailsCellModel?) {
            textView.text = item?.title
            Glide.with(imageView.context)
                .load(item?.thumbnail?.path?.replace("http","https")+"."+item?.thumbnail?.extension)
                .into(imageView)

            imageView.setOnClickListener {
                val intent = Intent(imageView.context, CharacterDetailsActivity::class.java)
                intent.putExtra("id", item?.id)
                imageView.context.startActivity(intent)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<ItemDetailsCellModel>() {
        override fun areItemsTheSame(oldItem: ItemDetailsCellModel, newItem: ItemDetailsCellModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ItemDetailsCellModel, newItem: ItemDetailsCellModel): Boolean {
            return oldItem == newItem
        }
    }
}