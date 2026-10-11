package io.mmaltsev.vkeducation.presentation.appdetails

import android.content.Context
import android.content.Intent
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails

internal fun createAppDetailsShareChooserIntent(
    context: Context,
    appDetails: AppDetails,
): Intent {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT,
            context.getString(
                R.string.app_details_share_text,
                appDetails.name,
                appDetails.developer,
                appDetails.description,
            ),
        )
    }

    return Intent.createChooser(
        sendIntent,
        context.getString(R.string.app_details_share_chooser_title),
    )
}
