package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme
import org.junit.Rule
import org.junit.Test

class AppDetailsScrollTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun developerSectionCanBeReachedByScrollingOnSmallScreen() {
        composeRule.setContent {
            VkEducationTheme {
                AppDetailsContent(
                    content = AppDetailsState.Content(
                        appDetails = AppDetails(
                            id = "app-id",
                            name = "Example app",
                            developer = "Example developer",
                            category = Category.APP,
                            ageRating = 3,
                            size = 8f,
                            iconUrl = "",
                            screenshotUrlList = emptyList(),
                            description = "Long description. ".repeat(100),
                        ),
                        descriptionCollapsed = true,
                    ),
                    onBackClick = {},
                    onShareClick = {},
                    onInstallClick = {},
                    onReadMoreClick = {},
                    onDeveloperClick = {},
                    modifier = Modifier
                        .width(320.dp)
                        .height(360.dp),
                )
            }
        }

        composeRule
            .onNodeWithText("Разработчик")
            .performScrollTo()
            .assertIsDisplayed()
    }
}
