package org.example.screens

import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import org.wikipedia.R

object OnboardingPageScreen : Screen<OnboardingPageScreen>() {
    val skipOnboardingButton = KButton {withId(R.id.fragment_onboarding_skip_button)}
}