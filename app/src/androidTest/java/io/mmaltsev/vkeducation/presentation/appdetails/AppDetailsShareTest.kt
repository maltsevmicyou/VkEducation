package io.mmaltsev.vkeducation.presentation.appdetails

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDetailsShareTest {
    @Test
    fun createsChooserWithCurrentAppDetailsAsPlainText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val appDetails = AppDetails(
            id = "app-id",
            name = "QuickNote",
            developer = "QuickSoft Inc",
            category = Category.PRODUCTIVITY,
            ageRating = 3,
            size = 8.2f,
            iconUrl = "",
            screenshotUrlList = emptyList(),
            description = "Заметки и списки дел",
        )

        val chooser = createAppDetailsShareChooserIntent(context, appDetails)

        assertEquals(Intent.ACTION_CHOOSER, chooser.action)
        assertEquals("Поделиться приложением", chooser.getCharSequenceExtra(Intent.EXTRA_TITLE))

        val sendIntent = chooser.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)
        assertNotNull(sendIntent)
        assertEquals(Intent.ACTION_SEND, sendIntent?.action)
        assertEquals("text/plain", sendIntent?.type)
        assertEquals(
            "QuickNote\nРазработчик: QuickSoft Inc\n\nЗаметки и списки дел",
            sendIntent?.getStringExtra(Intent.EXTRA_TEXT),
        )
    }
}
