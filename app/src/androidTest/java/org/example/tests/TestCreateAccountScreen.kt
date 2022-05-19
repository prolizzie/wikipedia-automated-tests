package org.example.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import io.github.kakaocup.kakao.common.utilities.getResourceString
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure
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

    private val username = "lizzie123"
    private val password = "password123"
    private val shortPassword = "12345"
    private val usernameHint = getResourceString(R.string.create_account_username_hint)
    private val passwordHint = getResourceString(R.string.account_creation_password_hint)
    private val repeatPasswordHint = getResourceString(R.string.create_account_password_repeat_hint)

    @Before
    fun skipOnboarding() {
        Allure.step("Пропускаем экран с настройками и подсказками при запуске приложения")
        OnboardingPageScreen {
            skipOnboardingButton.click()
        }
    }

    @Test
    @Description("Проверяем функцию скрытие пароля")
    fun testHidePassword() {
        MainScreen {
            Allure.step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
        }
        MoreScreen {
            Allure.step("Нажимаем \"Создать учетную запись\"")
            loginJoinWikipediaButton.click()
        }
        CreateAccountScreen {
            Allure.step("Вводим пароль")
            type(passwordHint, password)
            Allure.step("Нажимаем иконку \"глазик\"")
            clickHideIcon(passwordHint)
            Allure.step("Проверяем, что пароль скрыт")
            checkIsPasswordDisplayed(passwordHint)
            Allure.step("Нажимаем иконку \"глазик\"")
            clickHideIcon(passwordHint)
            Allure.step("Проверяем, что пароль не скрыт")
            checkIsPasswordHidden(passwordHint)
        }
    }

    @Test
    @Description("Проверяем, что при вводе слишком короткого пароля появляется сообщение об ошибке")
    fun checkPasswordValidation() {
        MainScreen {
            Allure.step("Нажимаем на кнопку \"Ещё\"")
            moreButton.click()
        }
        MoreScreen {
            Allure.step("")
            loginJoinWikipediaButton.click()
        }
        CreateAccountScreen {
            Allure.step("Вводим имя пользователя")
            type(usernameHint, username)
            Allure.step("Вводим пароль")
            type(passwordHint, shortPassword)
            Allure.step("Подтверждаем пароль")
            type(repeatPasswordHint, shortPassword)
            Allure.step("Нажимаем на кнопку \"Далее\"")
            nextButton.click()
            Allure.step("Проверяем отображение ошибки")
            checkErrorIsDisplayed()
        }
    }
}