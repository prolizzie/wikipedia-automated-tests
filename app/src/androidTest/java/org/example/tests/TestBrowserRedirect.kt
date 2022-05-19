package org.example.tests

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.Description
import org.example.screens.MainScreen
import org.example.screens.MoreScreen
import org.example.screens.OnboardingPageScreen
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

@RunWith(AllureAndroidJUnit4::class)
class TestBrowserRedirect {
    @get:Rule
    var rule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Before
    fun skipOnboarding() {
        Allure.step("Пропускаем экран с настройками и подсказками при запуске приложения")
        OnboardingPageScreen {
            skipOnboardingButton.click()
        }
    }

    @Test
    @Description("Проверяем редирект в браузер при нажатии кнопки \"Пожертвовать\"")
    fun testBrowserRedirect() {
        MainScreen {
            Allure.step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
        }
        MoreScreen {
            Allure.step("Нажимаем на кнопку \"Пожертвовать\"")
            donateButton.click()
        }

        Thread.sleep(1000)
        Allure.step("Проверяем, что произошел редирект в браузер")
        Intents.intended(AllOf.allOf(CoreMatchers.not(IntentMatchers.isInternal())))
    }
}