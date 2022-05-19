package org.example.screens

import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.wikipedia.R

object MainScreen : Screen<MainScreen>() {
    val moreButton = KButton { withId(R.id.nav_more_container) }
    val searchBar = KTextView {withId(R.id.search_container)}
}