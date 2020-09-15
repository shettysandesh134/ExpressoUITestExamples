package com.sandeshshetty.expressouitestexamples.data

data class Movie (
    val id: Int,
    val title: String,
    val image: String,
    var description: String,
    val directors: ArrayList<String>?,
    val star_actors: ArrayList<String>?
)