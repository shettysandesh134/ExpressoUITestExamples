package com.sandeshshetty.expressouitestexamples.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.sandeshshetty.expressouitestexamples.R
import com.sandeshshetty.expressouitestexamples.data.source.MoviesDataSource
import com.sandeshshetty.expressouitestexamples.data.source.MoviesRemoteDataSource


import com.sandeshshetty.expressouitestexamples.factory.MovieFragmentFactory
import com.sandeshshetty.expressouitestexamples.ui.movie.MovieDetailFragment
import com.sandeshshetty.expressouitestexamples.ui.movie.MovieListFragment
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(), UICommunicationListener {

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        init()
    }

    private fun init(){
        // pre recyclerview
//        if(supportFragmentManager.fragments.size == 0){
//            val movieId = 1
//            val bundle = Bundle()
//            bundle.putInt("movie_id", movieId)
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
//                .commit()
//        }
            // post recycler view
            if(supportFragmentManager.fragments.size == 0){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment::class.java, null)
                    .commit()
            }
    }

    private fun initDependencies(){

        // glide
        requestOptions = RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        // Data Source
        moviesDataSource = MoviesRemoteDataSource()
    }

    override fun loading(isLoading: Boolean) {
        if (isLoading)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.INVISIBLE
    }

}

