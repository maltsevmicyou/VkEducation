package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.mmaltsev.vkeducation.presentation.theme.VkEducationTheme

@Composable
fun InstallButton(
    installState: InstallState, // <-- ДОБАВЛЯЕМ ЭТОТ ПАРАМЕТР
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Определяем текст в зависимости от состояния
    val buttonText = when (installState) {
        is InstallState.NotInstalled -> "Установить"
        is InstallState.Installing -> "Отменить (${installState.progress}%)"
        is InstallState.Installed -> "Установлено"
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
        // Кнопка активна всегда, чтобы можно было нажать "Отменить" во время установки
    ) {
        Text(text = buttonText)
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        InstallButton(
            installState = InstallState.Installing(45), // Для превью покажем состояние установки
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}