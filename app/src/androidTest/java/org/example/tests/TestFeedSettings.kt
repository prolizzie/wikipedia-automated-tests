package org.example.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import io.github.kakaocup.kakao.common.utilities.getResourceString
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure.step
import io.qameta.allure.kotlin.Description
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.wikipedia.main.MainActivity
import org.example.screens.*
import org.junit.runner.RunWith
import org.wikipedia.R


@RunWith(AllureAndroidJUnit4::class)
class TestFeedSettings {

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

    private val featuredArticle = getResourceString(R.string.view_featured_article_card_title)
    private val topRead = getResourceString(R.string.view_top_read_card_title)
    private val pictureOfTheDay = getResourceString(R.string.view_featured_image_card_title)
    private val becauseYouRead = getResourceString(R.string.view_because_you_read_card_title)
    private val inTheNews = getResourceString(R.string.view_card_news_title)
    private val onThisDay = getResourceString(R.string.on_this_day_card_title)
    private val randomizer = getResourceString(R.string.view_random_card_title)
    private val todayOnWiki = getResourceString(R.string.view_main_page_card_title)
    private val articleName = "Тинькофф премьер лига"

    @Test
    @Description("Проверяем чекбоксы на странице настройки ленты")
    fun testFeedSettings() {
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
            step("Нажимаем на кнопку \"Настройки ленты\"")
            exploreFeedButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        step("Проверяем, что каждый чек-бокс в состоянии checked")
        CustomizeFeedScreen {
            assertCheckboxIsChecked(featuredArticle)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(topRead)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(pictureOfTheDay)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(becauseYouRead)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(inTheNews)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(onThisDay)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(randomizer)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            assertCheckboxIsChecked(todayOnWiki)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
    }

//    @Test
//    @Description("Проверяем добавление статьи в избранное")
//    fun checkAddingArticleToFavourites() {
//        MainScreen {
//            step("Нажимаем на поле поиска")
//            searchBar.click()
//        }
//        SearchScreen {
//            step("Вводим название статьи в строке поиска")
//            typeArticleName(articleName)
//        }
//    }
}