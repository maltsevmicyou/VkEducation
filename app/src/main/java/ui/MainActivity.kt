package io.mmaltsev.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import ui.AppNavigation // Убедись, что импорт правильный!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation() // ВОТ ОНА! Без этой строчки будет белый экран
            }
        }
    }
}