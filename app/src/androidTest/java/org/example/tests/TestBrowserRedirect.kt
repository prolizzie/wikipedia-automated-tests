package org.example.tests

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure.step
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

    @get:Rule
    val logcatRule = ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end")


    @Before
    fun skipOnboarding() {
        step("Пропускаем экран с настройками и подсказками при запуске приложения")
        OnboardingPageScreen {
            skipOnboardingButton.click()
        }
        allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
    }

    @Test
    @Description("Проверяем редирект в браузер при нажатии кнопки \"Пожертвовать\"")
    fun testBrowserRedirect() {
        MainScreen {
            step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        MoreScreen {
            step("Нажимаем на кнопку \"Пожертвовать\"")
            donateButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }

        Thread.sleep(1000)
        step("Проверяем, что произошел редирект в браузер")
        Intents.intended(AllOf.allOf(CoreMatchers.not(IntentMatchers.isInternal())))
        allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
    }
}