package com.espressoplayground.robots

import android.app.Activity
import android.content.ComponentName
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything

open class BaseTestRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

    fun clickButton(resId: Int): ViewInteraction = onView((withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }

    fun closeKeyboard() = ViewActions.closeSoftKeyboard()

    fun <T> isActivityLaunched(thisActivity: Activity, launchedActivity: Class<T>) =
        intended(hasComponent(ComponentName(thisActivity, launchedActivity)))
}