package com.sandeshshetty.expressouitestexamples.data.source

import com.sandeshshetty.expressouitestexamples.data.Movie

interface MoviesDataSource {

    fun getMovies(): List<Movie>

    fun getMovie(movieId: Int): Movie?

}