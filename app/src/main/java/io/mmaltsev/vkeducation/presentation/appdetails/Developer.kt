package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme

@Composable
fun Developer(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(text = name)
            Text(text = stringResource(R.string.app_details_developer))
        }
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        Developer(
            name = "VK Play",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}