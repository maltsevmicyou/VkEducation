package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.R
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme

@Composable
fun AppDetailsError(
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.app_details_error),
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = onRefreshClick,
        ) {
            Text(text = stringResource(R.string.app_details_error_refresh))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        AppDetailsError(
            onRefreshClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}