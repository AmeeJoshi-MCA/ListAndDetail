package com.myapplication.ui.itemList


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.myapplication.R
import com.myapplication.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class ItemListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //This rule tells android test to run everything one by one ,
    // without this u will get a error saying cannot complete task

    @Before
    fun setUp() {
        hiltRule.inject() // 6
    }

    @Test
    fun test_click_on_a_item_and_navigate_to_DetailFragment() {
        ActivityScenario.launch(MainActivity::class.java)

        val firstItemPosition = 0

        onView(withId(R.id.recycler_view_items)).check(matches(isDisplayed()))

        onView(withId(R.id.recycler_view_items))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ItemListAdapter.ViewHolderContent>(
                    firstItemPosition,
                    ViewActions.click()
                )
            )

    }

    @Test
    fun test_click_on_a_item_and_navigate_to_DetailFragment_and_check_items_displayed() {
        ActivityScenario.launch(MainActivity::class.java)

        val itemPosition = 4

        onView(withId(R.id.recycler_view_items))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ItemListAdapter.ViewHolderContent>(
                    itemPosition,
                    ViewActions.click()
                )
            )

        pressBack()

        onView(withId(R.id.recycler_view_items)).check(matches(isDisplayed()))
    }


}