package com.example.marvel.domain.repository

import com.example.marvel.R

enum class CharacterSpecificDetail {
    comics, series, stories, events;

    fun getRelativeRecyclerName(): Int {
        return when (this) {
            comics -> R.id.comicsRecyclerView
            series -> R.id.seriesRecyclerView
            stories -> R.id.storiesRecyclerView
            events -> R.id.eventsRecyclerView
        }
    }

    fun getRelativeTextView(): Int {
        return when (this) {
            comics -> R.id.comicsTextView
            series -> R.id.seriesTextView
            stories -> R.id.storiesTextView
            events -> R.id.eventsTextView
        }
    }
}