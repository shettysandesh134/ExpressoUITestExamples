package com.sandeshshetty.expressouitestexamples.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sandeshshetty.expressouitestexamples.R
import com.sandeshshetty.expressouitestexamples.data.FakeMovieData
import com.sandeshshetty.expressouitestexamples.ui.MainActivity
import com.sandeshshetty.expressouitestexamples.ui.movie.DirectorsFragment.Companion.stringBuilderForDirectors
import com.sandeshshetty.expressouitestexamples.ui.movie.MoviesListAdapter.*
import com.sandeshshetty.expressouitestexamples.ui.movie.StarActorsFragment.Companion.stringBuilderForStarActors
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    /**
     * Recyclerview comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select list item, nav to DetailFragment
     * Correct movie is in view
     */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    /**
     * select list item, nav to DetailFragment
     * pressBack
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * select list item, nav to DetailFragment
     * select directors textview, nav to DirectorsFragment
     */
    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        onView(withId(R.id.directors_text)).check(matches(withText(stringBuilderForDirectors(MOVIE_IN_TEST.directors!!))))
    }
    /**
     * select list item, nav to DetailFragment
     * select star actors textview, nav to StarActorsFragment
     */
    @Test
    fun test_navStarActorsFragment_validateStarActorsList() {

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        onView(withId(R.id.star_actors_text)).check(matches(withText(stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!))))
    }
}