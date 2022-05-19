package org.example.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import io.github.kakaocup.kakao.screen.Screen
import org.wikipedia.R

object AboutScreen : Screen<AboutScreen>() {

    fun assertContributorsDisplayed() {
        onView(ViewMatchers.withText(R.string.about_contributors_heading))
                .check(matches(isDisplayed()))
    }

    fun assertTranslatorsDisplayed() {
        onView(ViewMatchers.withText(R.string.about_translators_heading))
                .check(matches(isDisplayed()))
    }

    fun assertLicenseDisplayed() {
        onView(ViewMatchers.withText(R.string.about_app_license_heading))
                .check(matches(isDisplayed()))
    }
}