package com.sandeshshetty.expressouitestexamples.ui.util

import androidx.test.espresso.IdlingRegistry
import com.sandeshshetty.expressouitestexamples.util.EspressoIdlingResource
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.Exception

/**
 * Option 1
 * This option is much more difficult to read and is more verbose
 */
//class EspressoIdlingResourceRule: TestRule {

//    override fun apply(base: Statement?, description: Description?): Statement {
//
//        return IdlingResourceStatement(base)
//    }
//
//    class IdlingResourceStatement(private val base: Statement?): Statement() {
//
//        private val idlingResource = EspressoIdlingResource.countingIdlingResource
//
//        override fun evaluate() {
//
//            // Before
//            IdlingRegistry.getInstance().register(idlingResource)
//            try {
//                base?.evaluate()
//                    ?: throw Exception("Error evaluating test. Base statment is null.")
//            }finally {
//
//                // After
//                IdlingRegistry.getInstance().unregister(idlingResource)
//            }
//        }
//
//    }
//}

/**
 * Option 2
 * Simplified version of optoin#1 (TestWatcher class implements TestRule
 */
class EspressoIdlingResourceRule: TestWatcher() {

    private val idlingResource = EspressoIdlingResource.countingIdlingResource

    override fun starting(description: Description?) {

        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    override fun finished(description: Description?) {

        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}

