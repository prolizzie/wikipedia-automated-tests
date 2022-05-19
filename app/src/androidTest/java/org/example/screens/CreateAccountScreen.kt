package org.example.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import org.example.matchers.CustomMatchers
import org.example.matchers.CustomMatchers.Companion.isPasswordHidden
import org.hamcrest.Matchers.*
import org.hamcrest.core.AllOf.allOf

import org.wikipedia.R


object CreateAccountScreen : Screen<CreateAccountScreen>() {
    val nextButton = KButton { withId(R.id.create_account_submit_button) }

    fun type(hint: String, text: String) {
        onView(allOf(
                withClassName(endsWith("EditText")),
                isDescendantOfA(CustomMatchers.HintMatcher(hint))))
                .perform(clearText(), typeText(text))
    }

    fun clickHideIcon(hint: String) {
        onView(allOf(
                withId(R.id.text_input_end_icon),
                isDescendantOfA(CustomMatchers.HintMatcher(hint))))
                .perform(click())
    }

    fun checkIsPasswordDisplayed(hint: String) {
        onView(allOf(
                withClassName(endsWith("EditText")),
                isDescendantOfA(CustomMatchers.HintMatcher(hint))))
                .check(matches(not(isPasswordHidden())))
    }

    fun checkIsPasswordHidden(hint: String) {
        onView(allOf(
                withClassName(endsWith("EditText")),
                isDescendantOfA(CustomMatchers.HintMatcher(hint))))
                .check(matches(isPasswordHidden()))
    }

    fun checkErrorIsDisplayed() {
        onView(withText(R.string.create_account_password_error))
                .check(matches(allOf(
                        isDisplayed(),
                        hasTextColor(R.color.red50))))
    }
}
