package org.example.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure.step
import io.qameta.allure.kotlin.Description
import org.example.screens.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity

@RunWith(AllureAndroidJUnit4::class)
class TestAboutWikiScreen {
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
    @Description("Проверяем отображение блоков на странице \"О Википедии\"")
    fun testAboutWikiScreen() {
        MainScreen {
            step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        MoreScreen {
            step("Открываем настройки")
            settingsButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        SettingsScreen {
            step("Скроллим до раздела \"О приложении\"")
            scrollToAbout()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
            step("Нажимаем на \"О приложении \"Википедия\"")
            aboutWikiKButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        AboutScreen {
            step("Проверяем, что на экране есть блок \"Авторы\"")
            assertContributorsDisplayed()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Проверяем, что на экране есть блок \"Переводчики\"")
            assertTranslatorsDisplayed()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Проверяем, что на экране есть блок \"Лицензия\"")
            assertLicenseDisplayed()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
    }
}