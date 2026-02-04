package com.example.ivanpro

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.ivanpro.ui.theme.IvanProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Draw behind system bars to allow custom colors
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            IvanProTheme {
                // Logic for Navigation Bar Appearance
                val view = LocalView.current
                val darkTheme = isSystemInDarkTheme()
                
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as ComponentActivity).window
                        
                        // Set status and nav bar colors to transparent to let Compose background show
                        window.statusBarColor = Color.Transparent.toArgb()
                        window.navigationBarColor = Color.Transparent.toArgb()
                        
                        // Dynamic Icons Logic: 
                        // If app background is Light (darkTheme=false), we need Black icons (isAppearanceLightNavigationBars = true)
                        // If app background is Dark (darkTheme=true), we need White icons (isAppearanceLightNavigationBars = false)
                        val insetsController = WindowCompat.getInsetsController(window, view)
                        insetsController.isAppearanceLightStatusBars = !darkTheme
                        insetsController.isAppearanceLightNavigationBars = !darkTheme
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    // Box handles the layout. 
    // imePadding() ensures content pushes up when keyboard opens (Requires adjustResize in Manifest)
    // navigationBarsPadding() ensures we don't draw text under the gesture bar/buttons
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        // Example: If this is a wrapper for the Node.js project, we might use a WebView
        // Or a native UI. Here is a placeholder for the content.
        
        /* 
           NOTE: If this is a WebView wrapper for the hosted Node app:
           AndroidView(factory = { context ->
               WebView(context).apply {
                   settings.javaScriptEnabled = true
                   webViewClient = WebViewClient()
                   loadUrl("https://your-node-app-url.com")
               }
           }, modifier = Modifier.fillMaxSize())
        */

        Text(
            text = "Ivan Version 6 Model Pro\nNode.js Client",
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}