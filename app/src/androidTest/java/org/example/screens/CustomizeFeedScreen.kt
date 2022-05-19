package org.example.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.github.kakaocup.kakao.screen.Screen
import org.hamcrest.core.AllOf.allOf
import org.wikipedia.R

object CustomizeFeedScreen : Screen<CustomizeFeedScreen>() {

    fun assertCheckboxIsChecked(resString: String) {
        onView(allOf(
                withId(R.id.feed_content_type_checkbox),
                hasSibling(hasDescendant(withText(resString)))))
                .check(matches(isChecked()))
    }
}
