package com.myapplication.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.myapplication.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //This rule tells android test to run everything one by one ,
    // without this u will get a error saying cannot complete task

    @Before
    fun setUp() {
        hiltRule.inject() // 6
    }

    @Test
    fun test_launch_activity_and_check_items_fragment_title_displayed() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withText(R.string.items))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

}