package org.example.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import org.wikipedia.R


object SettingsScreen : Screen<SettingsScreen>() {
    val exploreFeedButton = KButton { withText(R.string.preference_title_customize_explore_feed) }
    val aboutWikiKButton = KButton { withText(R.string.about_description) }

    fun scrollToAbout() {
        onView(withId(R.id.recycler_view))
                .perform(scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(R.string.about_description))))
    }
}