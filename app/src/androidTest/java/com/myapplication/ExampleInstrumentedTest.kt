package com.myapplication

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myapplication.ui.MainActivityTest
import com.myapplication.ui.itemDetail.ItemDetailFragmentTest
import com.myapplication.ui.itemList.ItemListFragmentTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.runners.Suite

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@Suite.SuiteClasses(MainActivityTest::class, ItemListFragmentTest::class, ItemDetailFragmentTest::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.myapplication", appContext.packageName)
    }
}