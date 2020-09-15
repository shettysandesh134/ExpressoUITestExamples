package com.sandeshshetty.expressouitestexamples

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.app.Instrumentation.*
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class MainActivityTest {

    @get: Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    // just checks if an correct image is selected will it be returned in onActivityResult and displayed
    // developer.android.com/training/testing/espresso/intents
    @Test
    fun test_validateIntentSentToPickPackage() {

        // GIVEN
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )
        val activityResult = createGallerPickActivityResultStub()
        intending(expectedIntent).respondWith(activityResult)  // keyword to test intent

        // Execute and verify
        onView(withId(R.id.button_open_gallery)).perform(click())
        intending(expectedIntent)

    }

    private fun createGallerPickActivityResultStub(): ActivityResult {
        val resources: Resources = InstrumentationRegistry.getInstrumentation()
            .context.resources
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resources.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                    resources.getResourceEntryName(R.drawable.ic_launcher_background)
        )
        val resultIntent = Intent()
        resultIntent.setData(imageUri)
        return ActivityResult(RESULT_OK, resultIntent)
    }

    @Test
    fun test_isActivityInView() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java) // launching the activity for test

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_nextButton() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title)).check(matches(isDisplayed()))

        onView(withId(R.id.button_next_activity)).check(matches(isDisplayed())) // method 1 to test visibility

        onView(withId(R.id.button_next_activity)).check(matches(withEffectiveVisibility(Visibility.VISIBLE))) // method2
    }

    @Test
    fun test_isTitleTextDisplayed() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title)).check(matches(withText(R.string.text_mainactivity)))
    }

    @Test
    fun test_navSecondaryActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click()) // performing a click event on nextbutton

        onView(withId(R.id.secondary)).check(matches(isDisplayed())) // checking if the secondaryActivity is displayed now
    }

    @Test
    fun test_backPress_toMainActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click()) // performing a click event on nextbutton

        onView(withId(R.id.secondary)).check(matches(isDisplayed())) // checking if the secondaryActivity is displayed now

   //     onView(withId(R.id.button_back)).perform(click()) // perform click on back button on SecondaryActivity method1

        pressBack()  // method2

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
}