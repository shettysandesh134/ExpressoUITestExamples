package com.sandeshshetty.expressouitestexamples

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

// to check imageView widget has a drawable set
object ImageViewHasDrawableMatcher {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {

        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

            override fun describeTo(description: Description?) { // for debuggin
                description?.appendText("has drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean { // can chech something like if a particular bitmap is attached or not
                return item?.drawable != null
            }

        }
    }
}