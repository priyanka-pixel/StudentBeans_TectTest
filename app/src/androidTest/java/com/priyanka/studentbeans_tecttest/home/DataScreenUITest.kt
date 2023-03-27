package com.priyanka.studentbeans_tecttest.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.priyanka.studentbeans_tecttest.MainActivity
import com.priyanka.studentbeans_tecttest.StudentBeanApp
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DataScreenEndToEndTest {

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            StudentBeanApp()
        }
    }

    @Test
    fun nodeTextsOfDataTest() {
        composeRule.onRoot(useUnmergedTree = true).printToLog("Tag")
        composeRule.onAllNodesWithText(" ", useUnmergedTree = true)
            .assertCountEquals(0)
    }

    @Test
    fun checkDataTest() {
        composeRule.onRoot(useUnmergedTree = false).toString()
        composeRule.onNodeWithText("", useUnmergedTree = false)
            .assertDoesNotExist()
    }
    @After
    fun tearDown() {
    }
}