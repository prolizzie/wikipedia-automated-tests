package org.example.screens

import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import org.wikipedia.R

object MoreScreen : Screen<MoreScreen>() {
    val loginJoinWikipediaButton = KButton {withId(R.id.main_drawer_login_button)}
    val settingsButton = KButton {withId(R.id.main_drawer_settings_container)}
    val donateButton = KButton {withId(R.id.main_drawer_donate_container)}
}