package com.sandeshshetty.expressouitestexamples.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sandeshshetty.expressouitestexamples.R
import com.sandeshshetty.expressouitestexamples.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun test_MovieFragmentsNavigation() {

        // SETUP
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // VERIFY
        onView(withId(R.id.fragment_movie_directors_parent)).check(matches(isDisplayed()))

        // NAV BACK
        pressBack()

        // VERIFY
        onView(withId(R.id.fragment_moviedetail_parent)).check(matches(isDisplayed()))

        // NAV TO StarActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        //Verify
        onView(withId(R.id.fragment_movie_starsactors_parent)).check(matches(isDisplayed()))
    }
}