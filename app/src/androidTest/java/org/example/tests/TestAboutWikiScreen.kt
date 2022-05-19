package org.example.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure
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

    @Before
    fun skipOnboarding() {
        Allure.step("Пропускаем экран с настройками и подсказками при запуске приложения")
        OnboardingPageScreen {
            skipOnboardingButton.click()
        }
    }

    @Test
    @Description("Проверяем отображение блоков на странице \"О Википедии\"")
    fun testAboutWikiScreen() {
        MainScreen {
            Allure.step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
        }
        MoreScreen {
            Allure.step("Открываем настройки")
            settingsButton.click()
        }
        SettingsScreen {
            Allure.step("Скроллим до раздела \"О приложении\"")
            scrollToAbout()
            Allure.step("Нажимаем на \"О приложении \"Википедия\"")
            aboutWikiKButton.click()
        }
        AboutScreen {
            Allure.step("Проверяем, что на экране есть блок \"Авторы\"")
            assertContributorsDisplayed()
            Allure.step("Проверяем, что на экране есть блок \"Переводчики\"")
            assertTranslatorsDisplayed()
            Allure.step("Проверяем, что на экране есть блок \"Лицензия\"")
            assertLicenseDisplayed()
        }
    }
}