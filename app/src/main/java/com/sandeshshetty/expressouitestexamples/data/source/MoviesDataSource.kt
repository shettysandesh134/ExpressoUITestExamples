package com.sandeshshetty.expressouitestexamples.data.source

import com.sandeshshetty.expressouitestexamples.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

}