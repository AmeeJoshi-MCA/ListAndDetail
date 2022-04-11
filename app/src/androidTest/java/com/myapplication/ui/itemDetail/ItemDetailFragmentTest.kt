package com.myapplication.ui.itemDetail

import android.os.Bundle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.myapplication.R
import com.myapplication.launchFragmentInHiltContainer
import com.myapplication.network.model.Product
import com.myapplication.network.model.SalePrice
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ItemDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //This rule tells android test to run everything one by one ,
    // without this u will get a error saying cannot complete task

    @Before
    fun setUp() {
        hiltRule.inject() // 6
    }

    @Test
    fun test_launch_details_fragment() {

        val product = Product("1", "Desc", "11", "name", SalePrice("1.1", "EUR"), "")

        val bundle = Bundle()
        bundle.putParcelable("product", product)
        launchFragmentInHiltContainer<ItemDetailFragment>(fragmentArgs = bundle)
    }

    @Test
    fun test_launch_detail_fragment_and_check_child_views_visibility() {
        val product = Product("1", "Desc", "11", "name", SalePrice("1.1", "EUR"), "")

        val bundle = Bundle()
        bundle.putParcelable("product", product)
        launchFragmentInHiltContainer<ItemDetailFragment>(fragmentArgs = bundle)

        Espresso.onView(withId(R.id.img_item))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(withId(R.id.textItemName))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(withId(R.id.textItemPrice))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }


    @Test
    fun test_launch_detail_fragment_and_check_if_passed_item_fields_displayed() {
        val productName = "Default Product Name"
        val productDesc = "Default Description"
        val product =
            Product("1", description = productDesc, "11", productName, SalePrice("1.1", "EUR"), "")

        val bundle = Bundle()
        bundle.putParcelable("product", product)
        launchFragmentInHiltContainer<ItemDetailFragment>(fragmentArgs = bundle)

        Espresso.onView(withId(R.id.textItemName))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withText(productName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}