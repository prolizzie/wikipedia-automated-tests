package org.example.matchers

import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatchers {
    class HintMatcher(private val hint: String) :
            BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Check input field with hint = $hint")
        }

        override fun matchesSafely(foundView: TextInputLayout): Boolean {
            return foundView.hint == hint
        }
    }

    companion object {
        fun isPasswordHidden(): Matcher<View?>? {
            return object : BoundedMatcher<View?, EditText>(EditText::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("Password is hidden")
                }

                override fun matchesSafely(editText: EditText): Boolean {
                    return editText.transformationMethod is PasswordTransformationMethod
                }
            }
        }
    }
}

