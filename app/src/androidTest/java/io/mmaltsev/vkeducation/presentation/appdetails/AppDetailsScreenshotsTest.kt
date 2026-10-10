package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme
import org.junit.Rule
import org.junit.Test

class AppDetailsScreenshotsTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun screenshotsSectionOmittedWhenListIsNull() {
        setAppDetailsContent(screenshots = null)

        composeRule.onNodeWithText("Скриншоты").assertDoesNotExist()
    }

    @Test
    fun screenshotsSectionOmittedWhenListIsEmpty() {
        setAppDetailsContent(screenshots = emptyList())

        composeRule.onNodeWithText("Скриншоты").assertDoesNotExist()
    }

    @Test
    fun screenshotsSectionPagesThroughAllSuppliedUrls() {
        setAppDetailsContent(
            screenshots = (1..5).map { "file:///nonexistent-screenshot-$it.jpg" },
        )

        composeRule.onNodeWithText("Скриншоты").assertExists()
        composeRule.onNode(hasScrollAction()).performScrollToIndex(4)
    }

    private fun setAppDetailsContent(screenshots: List<String>?) {
        composeRule.setContent {
            VkEducationTheme {
                AppDetailsContent(
                    content = AppDetailsState.Content(
                        appDetails = AppDetails(
                            id = "app-id",
                            name = "Test app",
                            developer = "Test developer",
                            category = Category.APP,
                            ageRating = 0,
                            size = 1f,
                            iconUrl = "",
                            screenshotUrlList = screenshots,
                            description = "Test description",
                        ),
                        descriptionCollapsed = false,
                    ),
                    onBackClick = {},
                    onShareClick = {},
                    onInstallClick = {},
                    onReadMoreClick = {},
                    onDeveloperClick = {},
                )
            }
        }
    }
}
