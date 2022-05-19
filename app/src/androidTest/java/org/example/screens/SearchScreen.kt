package org.example.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import io.github.kakaocup.kakao.screen.Screen
import org.wikipedia.R

object SearchScreen : Screen<SearchScreen>() {

    fun typeArticleName(articleName: String) {
        onView(withId(R.id.search_src_text))
                .perform(replaceText(articleName))
    }
//    fun clickOnArticle(articleName: String) {
//        onView(withClassName(`is`("ConstraintLayout")))
//                .check()
//    }
}