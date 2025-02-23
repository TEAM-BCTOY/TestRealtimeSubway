package com.team_bctoy.testrealtimesubway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team_bctoy.testrealtimesubway.scene.ApiSelector
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestRealtimeSubwayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApiSelector(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestRealtimeSubwayTheme {
        ApiSelector()
    }
}