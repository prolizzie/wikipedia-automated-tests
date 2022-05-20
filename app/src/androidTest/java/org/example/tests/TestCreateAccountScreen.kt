package org.example.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import io.github.kakaocup.kakao.common.utilities.getResourceString
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure.step
import io.qameta.allure.kotlin.Description
import org.example.screens.CreateAccountScreen
import org.example.screens.MainScreen
import org.example.screens.MoreScreen
import org.example.screens.OnboardingPageScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.R
import org.wikipedia.main.MainActivity

@RunWith(AllureAndroidJUnit4::class)
class TestCreateAccountScreen {

    @get:Rule
    var rule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @get:Rule
    val logcatRule = ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end")

    private val username = "lizzie123"
    private val password = "password123"
    private val shortPassword = "12345"
    private val usernameHint = getResourceString(R.string.create_account_username_hint)
    private val passwordHint = getResourceString(R.string.account_creation_password_hint)
    private val repeatPasswordHint = getResourceString(R.string.create_account_password_repeat_hint)

    @Before
    fun skipOnboarding() {
        step("Пропускаем экран с настройками и подсказками при запуске приложения")
        OnboardingPageScreen {
            skipOnboardingButton.click()
        }
        allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
    }

    @Test
    @Description("Проверяем функцию скрытие пароля")
    fun testHidePassword() {
        MainScreen {
            step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        MoreScreen {
            step("Нажимаем \"Создать учетную запись\"")
            loginJoinWikipediaButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        CreateAccountScreen {
            step("Вводим пароль")
            type(passwordHint, password)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Нажимаем иконку \"глазик\"")
            clickHideIcon(passwordHint)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Проверяем, что пароль скрыт")
            checkIsPasswordDisplayed(passwordHint)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Нажимаем иконку \"глазик\"")
            clickHideIcon(passwordHint)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Проверяем, что пароль не скрыт")
            checkIsPasswordHidden(passwordHint)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
    }

    @Test
    @Description("Проверяем, что при вводе слишком короткого пароля появляется сообщение об ошибке")
    fun checkPasswordValidation() {
        MainScreen {
            step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        MoreScreen {
            step("")
            loginJoinWikipediaButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
        CreateAccountScreen {
            step("Вводим имя пользователя")
            type(usernameHint, username)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Вводим пароль")
            type(passwordHint, shortPassword)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Подтверждаем пароль")
            type(repeatPasswordHint, shortPassword)
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Нажимаем на кнопку \"Далее\"")
            nextButton.click()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)

            step("Проверяем отображение ошибки")
            checkErrorIsDisplayed()
            allureScreenshot(name = "ss_step", quality = 90, scale = 1.0f)
        }
    }
}