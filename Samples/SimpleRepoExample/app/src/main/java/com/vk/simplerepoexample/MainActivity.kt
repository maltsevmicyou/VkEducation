package com.vk.simplerepoexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vk.simplerepoexample.data.RuStoreRepository
import com.vk.simplerepoexample.data.RuStoreRepositoryImpl
import com.vk.simplerepoexample.ui.theme.SimpleRepoExampleTheme
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {

    private companion object {
        private const val TAG = "Simple Example"
    }

    private val ruStoreRepository: RuStoreRepository by lazy { RuStoreRepositoryImpl(this) }
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleRepoExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        executor.execute {
            ruStoreRepository.getApplications(
                onSuccess = { apps, type ->
                    Log.d(TAG, "type = ${type}, apps = ${apps.joinToString()}")
                },
                onFailed = {
                    Log.e(TAG, it.stackTraceToString())
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleRepoExampleTheme {
        Greeting("Android")
    }
}