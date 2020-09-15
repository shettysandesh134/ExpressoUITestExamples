package com.sandeshshetty.expressouitestexamples

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sandeshshetty.expressouitestexamples.MaterialDialogActivity.Companion.buildToastMessage
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MaterialDialogActivityTest {

    @Test
    fun test_showDialog_captureNameInput() {

        // GIVEN
        val activityScenario = ActivityScenario.launch(MaterialDialogActivity::class.java)
        val EXPECTED_NAME = "Sandesh"

        // Execute and Verify
        onView(withId(R.id.button_launch_dialog)).perform(click())

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed())) // checking if dialog is in view by looking for a particular text on the dialog

        //  dialog cannot be dismissed without entering text
        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // enter some input (since it is a material design input type dialog the id of edittext can be found in library.
        // just search https://github.com/afollestad/material-dialogs/blob/main/input/src/main/res/layout/md_dialog_stub_input.xml
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(click())

        // make sure dialog is gone
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        // confirm name is set to TextView in Activity
        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))

        // test if toast is displayed
        onView(withText(buildToastMessage(EXPECTED_NAME))).inRoot(ToastMatcher()).check(matches(
            isDisplayed()))
    }
}