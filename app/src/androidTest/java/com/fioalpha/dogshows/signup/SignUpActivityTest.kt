package com.fioalpha.dogshows.signup

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.fioalpha.dogshows.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
@LargeTest
class SignUpActivityTest: KoinTest {

    @get:Rule
    val activityRule = ActivityTestRule(SignUpActivity::class.java, true, true)

    private val email = "teste@Tets.com"

    @Test
    fun insertEmailToMakeSignUp() {
        onView(withId(R.id.sign_up_email)).perform(
            typeText(email),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.button)).perform(ViewActions.click())
    }

}
