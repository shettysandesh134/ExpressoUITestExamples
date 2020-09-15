package com.sandeshshetty.expressouitestexamples.data.source

import com.sandeshshetty.expressouitestexamples.data.DummyMovies.INFINITY_WAR
import com.sandeshshetty.expressouitestexamples.data.DummyMovies.THE_RUNDOWN
import com.sandeshshetty.expressouitestexamples.data.Movie

class MoviesRemoteDataSource: MoviesDataSource {

    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, Movie>(2)

    init {
        addMovie(
            0,
            "Avengers: Infinity War",
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/Infinity_War-infinity_war.png",
            "The Avengers and their allies must be willing to sacrifice all in an attempt to " +
                    "defeat the powerful Thanos before his blitz of devastation and ruin puts an end to " +
                    "the universe.",
            arrayListOf("Anthony Russo", "Joe Russo"),
            arrayListOf("Robert Downey Jr.", "Chris Hemsworth", "Mark Ruffalo", "+ more...")
        )
        addMovie(
            1,
            "The Rundown",
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
            "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                    "becomes involved in the fight against an oppressive town operator and the search " +
                    "for a legendary treasure.",
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        )
    }

    private fun addMovie(
        id: Int, title: String,
        image: String,
        description: String,
        directors: ArrayList<String>?,
        star_actors: ArrayList<String>?
    ) {
        val movie = Movie(
            id,
            title,
            image,
            description,
            directors,
            star_actors
        )
        MOVIES_REMOTE_DATA.put(id, movie)
    }

    override fun getMovie(movieId: Int): Movie? {
        return MOVIES_REMOTE_DATA[movieId]
    }




}
